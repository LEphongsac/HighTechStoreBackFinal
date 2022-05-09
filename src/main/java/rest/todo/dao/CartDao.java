package rest.todo.dao;

import rest.todo.ConnectionDB;
import rest.todo.model.Article;
import rest.todo.model.Cart;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CartDao {
    ArticleDao articleDao = new ArticleDao();

    @Context
    UriInfo uriInfo;
    @Context
    Request request;

    //get all Cart
    private static Connection CONNEXION = ConnectionDB.getDBConnection();

    public int getNumberProduct(int idUser){
        List<Integer> carts = new ArrayList<>();
        int totalNumber=0;
        try {
            PreparedStatement select = CONNEXION.prepareStatement("select * from cart where Cart.idUser = "+idUser);
            ResultSet rSelect = select.executeQuery();
            while (rSelect.next()) {
                int a = rSelect.getInt("quantity");
                carts.add(a);
            }
            for(int i=0; i<carts.size();i++){
                totalNumber = totalNumber + carts.get(i);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return totalNumber;
    }

    public List<Article> getAllProductOfCart(int idUser) {
        List<Article> Carts = new ArrayList<Article>();
        try {
            List<Cart> listCart = new ArrayList<>();
            listCart=getAllCart();
            PreparedStatement select = CONNEXION.prepareStatement("select * from article  , cart where article.id = cart.idProduct and cart.idUser ="+idUser);
            ResultSet rSelect = select.executeQuery();
            while (rSelect.next()) {
                for(Cart cart: listCart) {
                    if(cart.getIdProduct()==rSelect.getInt("id") && cart.getIdUser()==idUser){
                    Article a = new Article(
                            rSelect.getInt("id"),
                            rSelect.getString("label"),
                            rSelect.getString("marque"),
                            rSelect.getString("description"),
                            cart.getQuantity(),
                            rSelect.getString("photo"),
                            rSelect.getInt("idCategorie"),
                            rSelect.getInt("idUser"),
                            rSelect.getInt("price"));

                    Carts.add(a);
                }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Carts;
    }

    public List<Cart> getAllCart() {
        List<Cart> carts = new ArrayList<Cart>();

        try {
            PreparedStatement select = CONNEXION.prepareStatement("select * from cart");
            ResultSet rSelect = select.executeQuery();
            while (rSelect.next()) {
                Cart a = new Cart(
                        rSelect.getInt("idUser"),
                        rSelect.getInt("idProduct"),
                        rSelect.getInt("quantity"));
                carts.add(a);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return carts;
    }

    //insert article into Cart
    public boolean insertIntoCart(Cart c) throws SQLException {
        List<Cart> carts = new ArrayList<Cart>();
        try {
            PreparedStatement getCart = CONNEXION.prepareStatement("select * from cart where Cart.idProduct = " + c.getIdProduct() + " and Cart.idUser = " + c.getIdUser());
            ResultSet rSelect = getCart.executeQuery();
            while (rSelect.next()) {
                Cart a = new Cart(
                        rSelect.getInt("idUser"),
                        rSelect.getInt("idProduct"),
                        rSelect.getInt("quantity"));
                carts.add(a);
            }
            if(carts.isEmpty()) {
                String sql = "insert into Cart "
                        + " (idUser,idProduct,quantity)" + " values (?,?,?)";
                PreparedStatement select = CONNEXION.prepareStatement(sql);
                select.setInt(1, c.getIdUser());
                select.setInt(2, c.getIdProduct());
                select.setInt(3, 1);
                select.executeUpdate();
            }
            else{
                String sql = "update Cart set quantity= ? where Cart.idProduct ="+c.getIdProduct()+" and Cart.idUser="+c.getIdUser();
                PreparedStatement update = CONNEXION.prepareStatement(sql);
                update.setInt(1,carts.get(0).getQuantity()+1);
                update.executeUpdate();
            }

        }catch(Exception e){
            return false;
        }
        return true;
    }
    //delete article from cart
    public boolean deleteFromCart(int idProduct, int idUser) throws SQLException {
        try {
            int quantity=0;
            PreparedStatement select = CONNEXION.prepareStatement("select * from cart where Cart.idProduct = " + idProduct + " and Cart.idUser = " + idUser);
            ResultSet rSelect = select.executeQuery();
            while (rSelect.next()) {
                 quantity = rSelect.getInt("quantity");
            }

                if (quantity > 1) {
                    String sql = "update Cart set quantity= ? where Cart.idProduct ="+idProduct+" and Cart.idUser="+idUser;
                    PreparedStatement update = CONNEXION.prepareStatement(sql);
                    update.setInt(1,quantity-1);
                    update.executeUpdate();
                }
                else if(quantity==0){
                    return false;
                }
                else {
                    PreparedStatement delete = CONNEXION.prepareStatement("delete from Cart where Cart.idProduct = " + idProduct + " and Cart.idUser = " + idUser);
                    delete.executeUpdate();
                }



        }catch(Exception e){
            return false;
        }
        return true;
    }
    public boolean deleteFromCartByIdUser(int idUser) throws SQLException{
        try {
            PreparedStatement delete = CONNEXION.prepareStatement("delete from Cart where Cart.idUser = " + idUser);
            delete.executeUpdate();
        }catch(Exception e){
            return false;
        }
        return true;
    }

    public Integer totalPrice(int idUser) {
        List<Cart> listCart = getAllCart();
        int price=0;
        try {
            for (Cart cart : listCart) {
                if (cart.getIdUser() == idUser) {
                    //chercher le produit de cette utilisateur
                    Article article = articleDao.getArticle(cart.getIdProduct());
                    price +=article.getPrice();
                }
            }
        }catch(Exception e){
            return null;
        }
        return price;
    }
}