package rest.todo;

import java.sql.*;

public class ConnectionDB {
    private static Connection connection;
    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ProjetArchi",
                    "root", "");
            System.out.println("Connection Established");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public static Connection getDBConnection() {
        return connection;
    }
}

