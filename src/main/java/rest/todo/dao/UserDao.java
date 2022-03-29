package rest.todo.dao;

import rest.todo.ConnectionDB;
import rest.todo.model.Article;
import rest.todo.model.User;

import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class UserDao {
    //get all article
    private static Connection CONNEXION = ConnectionDB.getDBConnection();

    //getUsersById
    public User getUserById(UriInfo uriInfo, Request request,int idUser){
        User user = null;
        try {
            PreparedStatement select = CONNEXION.prepareStatement("SELECT * FROM users WHERE id ="+idUser);
            ResultSet rs = select.executeQuery();
            // Extract data from result set
            while (rs.next()) {
                User u = new User(idUser,
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("firstname"),
                        rs.getString("lastname"),
                        rs.getInt("role"));
                user = u;
            }
        } catch (SQLException e) {
            e.getMessage();
            e.printStackTrace();
        }
        if (user == null)
            throw new RuntimeException("Get: Article with " + idUser + " not found");
        return user;
    }

    public List<User> getAllUser() {
        List<User> user = new ArrayList<>();

        try {
            PreparedStatement select = CONNEXION.prepareStatement("select * from users");
            ResultSet rSelect = select.executeQuery();
            while (rSelect.next()) {
                User u = new User(
                        rSelect.getInt("id"),
                        rSelect.getString("username"),
                        rSelect.getString("password"),
                        rSelect.getString("firstname"),
                        rSelect.getString("lastname"),
                        rSelect.getInt("role"));
                user.add(u);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

//    //insert users
//    public void insertArticle(Article article) throws SQLException {
//        String sql = "insert into article "
//                + " (label,marque, description,photo,idCategorie,idUser,price)" + " values (?,?,?,?,?,?,?)";
//        PreparedStatement select = CONNEXION.prepareStatement(sql);
//        select.setString(1, article.getLabel());
//        select.setString(2, article.getMarque());
//        select.setString(3, article.getDescription());
//        select.setString(4, article.getPhoto());
//        select.setInt(5, article.getIdCategorie());
//        select.setInt(6, article.getIdUser());
//        select.setInt(7, article.getPrice());
//        select.executeUpdate();
//    }

    public void deleteUser(int idUser) throws SQLException {
        PreparedStatement select = CONNEXION.prepareStatement("delete from users where id = " + idUser);
        select.executeUpdate();

    }

    //updateUser
//    public void updateArticle(Article article,int idArticle) throws SQLException{
//        String sql = "update article set"
//                + " (label = ? ,marque=?, description=?,photo=?,idCategorie=?,idUser=?,price=?) where id ="+idArticle;
//        PreparedStatement select = CONNEXION.prepareStatement(sql);
//        select.setString(1, article.getLabel());
//        select.setString(2, article.getMarque());
//        select.setString(3, article.getDescription());
//        select.setString(4, article.getPhoto());
//        select.setInt(5, article.getIdCategorie());
//        select.setInt(6, article.getIdUser());
//        select.setInt(7, article.getPrice());
//        select.executeUpdate();
//    }

    public int authentication(String username, String password){
        int role=0;
        try {
            PreparedStatement select = CONNEXION.prepareStatement("SELECT * FROM users WHERE username ="+username+" and password="+password);
            ResultSet rs = select.executeQuery();
            // Extract data from result set
            role = rs.getInt("role");
        } catch (SQLException e) {
            e.getMessage();
            e.printStackTrace();
        }
        if (role == 0)
            throw new RuntimeException("Get: Article with " + username + " not found");
        return role;
    }
}
