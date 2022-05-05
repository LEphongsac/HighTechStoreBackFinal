package rest.todo.dao;

import rest.todo.ConnectionDB;
import rest.todo.model.Article;

import java.sql.*;
import java.util.*;


public class ArticleDao{
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
                        rSelect.getInt("quantity"),
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

    public List<Article> getAllArticleByCategorie(int idCategorie) {
        List<Article> articles = new ArrayList<Article>();

        try {
            PreparedStatement select = CONNEXION.prepareStatement("select * from article where idCategorie =" + idCategorie);
            ResultSet rSelect = select.executeQuery();
            while (rSelect.next()) {
                Article a = new Article(
                        rSelect.getInt("id"),
                        rSelect.getString("label"),
                        rSelect.getString("marque"),
                        rSelect.getString("description"),
                        rSelect.getInt("quantity"),
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

    //get article by id
    public Article getArticle( int id){
        Connection connection = ConnectionDB.getDBConnection();
        Statement statement;
        Article article = null;
        try {
            PreparedStatement select = connection.prepareStatement("SELECT * FROM article WHERE id ="+id);
            ResultSet rs = select.executeQuery();
            // Extract data from result set
            while (rs.next()) {
                Article a = new Article(
                        rs.getInt("id"),
                        rs.getString("label"),
                        rs.getString("marque"),
                        rs.getString("description"),
                        rs.getInt("quantity"),
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
    public Boolean insertArticle(Article article) throws SQLException {
        try {
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
        }catch(Exception e){
            return false;
        }
        return true;
    }

    public boolean deleteArticle(int idArticle) throws SQLException {
        try{
        PreparedStatement select = CONNEXION.prepareStatement("delete from article where id = " + idArticle);
        select.executeUpdate();
        }catch(Exception e){
            return false;
        }
            return true;
    }

    public Article updateArticle(Article article,int idArticle) throws SQLException{
        String sql = "update article set"
                + " label = ? ,marque = ?, description = ?,photo = ?,idCategorie = ?,idUser = ?,price = ? where id ="+idArticle;
        PreparedStatement select = CONNEXION.prepareStatement(sql);
        select.setString(1, article.getLabel());
        select.setString(2, article.getMarque());
        select.setString(3, article.getDescription());
        select.setString(4, article.getPhoto());
        select.setInt(5, article.getIdCategorie());
        select.setInt(6, article.getIdUser());
        select.setInt(7, article.getPrice());
        select.executeUpdate();

        return getArticle(idArticle);
    }
}