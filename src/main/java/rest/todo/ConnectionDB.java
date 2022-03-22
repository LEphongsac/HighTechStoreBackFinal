package rest.todo;

import java.sql.*;

public class ConnectionDB {
    static final String DB_URL = "jdbc:mysql://localhost:3306/projetarchi";
    static final String USER = "root";
    static final String PASS = "";
    static final String QUERY = "SELECT id, first, last, age FROM Registration";
    public static Connection getDBConnection() {
        Connection connection = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Connection Established");

        } catch (SQLException e) {
            e.getMessage();
            e.printStackTrace();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }
}

