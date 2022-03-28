package rest.todo.dao;

import rest.todo.ConnectionDB;
import rest.todo.model.Article;

import java.sql.*;
import java.util.*;

public class ArticleDao {
    //get all article
    private static Connection CONNEXION = ConnectionDB.getDBConnection();
    public List<Article> getAllArticle() {
        List<Article> articles = new ArrayList<Article>();

        try {
            PreparedStatement select = CONNEXION.prepareStatement("select * from article");
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
                articles.add(a);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return articles;
    }

    //insert article
    public void insertArticle(Article article) throws SQLException {
        String sql = "insert into article "
                + " (label,marque, description,photo,idCategorie,idUser,price)" + " values (?,?,?,?,?,?,?)";
        PreparedStatement select = CONNEXION.prepareStatement(sql);
        select.setString(1, article.getLabel());
        select.setString(2, article.getMarque());
        select.setString(3, article.getDescription());
        select.setString(4, article.getPhoto());
        select.setInt(5, article.getIdCategorie());
        select.setInt(6, article.getIdUser());
        select.setInt(7, article.getPrice());
        select.executeUpdate();
    }
}
