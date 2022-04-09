package rest.todo.dao;

import rest.todo.ConnectionDB;
import rest.todo.model.Article;
import rest.todo.model.Cart;

import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CartDao {
    //get all Cart
    private static Connection CONNEXION = ConnectionDB.getDBConnection();
    public List<Article> getAllProductOfCart(int idUser) {
        List<Article> Carts = new ArrayList<Article>();

        try {
            PreparedStatement select = CONNEXION.prepareStatement("select label, marque,price from Cart, article where Cart.idProduct= article.id and Cart.idUser=" +idUser);
            ResultSet rSelect = select.executeQuery();
            while (rSelect.next()) {
                Article a = new Article(
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

    //insert article into Cart
    public void insertIntoCart(Article a) throws SQLException {
        String sql = "insert into Cart "
                + " (idUser,idProduct)" + " values (?,?)";
        PreparedStatement select = CONNEXION.prepareStatement(sql);
        select.setInt(1, a.getIdUser());
        select.setInt(2,a.getId());
        select.executeUpdate();
    }
    //delete article from cart
    public void deleteFromCart(int idProduct) throws SQLException{
        PreparedStatement delete = CONNEXION.prepareStatement("delete from Cart where Cart.idProduct = " + idProduct);
        delete.executeUpdate();
    }
}
