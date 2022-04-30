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
    public List<Article> getAllProductOfCart(int idUser) {

        List<Article> Carts = new ArrayList<Article>();

        try {
            PreparedStatement select = CONNEXION.prepareStatement("select * from article  , cart where article.id = cart.idProduct and cart.idUser ="+idUser);
            ResultSet rSelect = select.executeQuery();
            while (rSelect.next()) {
                Article a = new Article(
                        rSelect.getInt("id"),
                        rSelect.getString("label"),
                        rSelect.getString("marque"),
                        rSelect.getString("description"),
                        rSelect.getString("photo"),
                        rSelect.getInt("idCategorie"),
                        rSelect.getInt("idUser"),
                        rSelect.getInt("price"));

                Carts.add(a);
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
        try {
        String sql = "insert into Cart "
                + " (idUser,idProduct)" + " values (?,?)";
        PreparedStatement select = CONNEXION.prepareStatement(sql);
        select.setInt(1, c.getIdUser());
        select.setInt(2,c.getIdProduct());
        select.executeUpdate();
        }catch(Exception e){
            return false;
        }
        return true;
    }
    //delete article from cart
    public boolean deleteFromCart(int idProduct) throws SQLException{
        try {
            PreparedStatement delete = CONNEXION.prepareStatement("delete from Cart where Cart.idProduct = " + idProduct);
            delete.executeUpdate();
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
                    Article article = articleDao.getArticle(uriInfo,request,cart.getIdProduct());
                    price +=article.getPrice();
                }
            }
        }catch(Exception e){
            return null;
        }
        return price;
    }
}