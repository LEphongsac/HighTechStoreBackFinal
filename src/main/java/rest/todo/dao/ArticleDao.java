package rest.todo.dao;

import rest.todo.ConnectionDB;
import rest.todo.model.Article;
import rest.todo.model.Categorie;

import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;
import java.sql.*;
import java.util.*;


public class ArticleDao{
    //get all article
    private static Connection CONNEXION = ConnectionDB.getDBConnection();
    public List<Article> getAllArticle(int idCategorie) {
        List<Article> articles = new ArrayList<Article>();

        try {
            PreparedStatement select = CONNEXION.prepareStatement("select * from article where idCategorie =" + idCategorie);
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

    public Article getArticle(UriInfo uriInfo, Request request, int id){
        Connection connection = ConnectionDB.getDBConnection();
        Statement statement;
        Article article = null;
        try {
            PreparedStatement select = connection.prepareStatement("SELECT * FROM article WHERE id ="+id);
            ResultSet rs = select.executeQuery();
            // Extract data from result set
            while (rs.next()) {
                Article a = new Article(
                        rs.getString("label"),
                        rs.getString("marque"),
                        rs.getString("description"),
                        rs.getString("photo"),
                        rs.getInt("idCategorie"),
                        rs.getInt("idUser"),
                        rs.getInt("price"));
                article = a;
            }
        } catch (SQLException e) {
            e.getMessage();
            e.printStackTrace();
        }
        if (article == null)
            throw new RuntimeException("Get: Article with " + id + " not found");
        return article;
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
