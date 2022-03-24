package rest.todo.dao;

import rest.todo.ConnectionDB;
import rest.todo.model.Article;

import java.sql.*;
import java.util.*;

public class ArticleDao {
    //get all article
    public List<Article> getAllArticle() {
        List<Article> articles = new ArrayList<Article>();
        Connection con = ConnectionDB.getDBConnection();
        try {
            PreparedStatement select = con.prepareStatement("select * from article");
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
                articles.add(a);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return articles;
    }

    //insert article
    //public void insertArticle()
}
