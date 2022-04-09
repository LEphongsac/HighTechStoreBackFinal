package rest.todo.dao;

import rest.todo.ConnectionDB;
import rest.todo.model.Article;
import rest.todo.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDao {
    private static Connection CONNEXION = ConnectionDB.getDBConnection();

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try {
            PreparedStatement select = CONNEXION.prepareStatement("select * from user" );
            ResultSet rSelect = select.executeQuery();
            while (rSelect.next()) {
                User a = new User(
                        rSelect.getString("email"),
                        rSelect.getString("password"),
                        rSelect.getString("firstname"),
                        rSelect.getString("lastname"),
                        rSelect.getString("role"));
                users.add(a);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }
    public boolean login(String username, String password){
        List<User> listUser = getAllUsers() ;
        for(User user:listUser){
            if(user.getEmail().equalsIgnoreCase(username) && user.getPassword().equals(password)){
                return true;
            }
        }
        return false;
    }

    public User findUserByEmail(String email) {
            List<User> listUser = getAllUsers();
        for(User user:listUser){
                if(user.getEmail().equalsIgnoreCase(email)){
                    return user;
                }
            }
            return null;
    }
}
