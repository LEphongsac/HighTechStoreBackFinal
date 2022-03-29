//package rest.todo.dao;
//
//import rest.todo.ConnectionDB;
//import rest.todo.model.Cart;
//
//import javax.ws.rs.core.Request;
//import javax.ws.rs.core.UriInfo;
//import java.sql.*;
//import java.util.ArrayList;
//import java.util.List;
//
//public class CartDao {
//    //get all Cart
//    private static Connection CONNEXION = ConnectionDB.getDBConnection();
//    public List<Cart> getAllCart() {
//        List<Cart> Carts = new ArrayList<Cart>();
//
//        try {
//            PreparedStatement select = CONNEXION.prepareStatement("select * from Cart");
//            ResultSet rSelect = select.executeQuery();
//            while (rSelect.next()) {
//                Cart a = new Cart(
//                        rSelect.getString("label"),
//                        rSelect.getString("marque"),
//                        rSelect.getString("description"),
//                        rSelect.getString("photo"),
//                        rSelect.getInt("idCategorie"),
//                        rSelect.getInt("idUser"),
//                        rSelect.getInt("price"));
//                Carts.add(a);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return Carts;
//    }
//
//    public Cart getCart(UriInfo uriInfo, Request request, int id){
//        Connection connection = ConnectionDB.getDBConnection();
//        Statement statement;
//        Cart Cart = null;
//        try {
//            PreparedStatement select = connection.prepareStatement("SELECT * FROM Cart WHERE id ="+id);
//            ResultSet rs = select.executeQuery();
//            // Extract data from result set
//            while (rs.next()) {
//                Cart a = new Cart(
//                        rs.getString("label"),
//                        rs.getString("marque"),
//                        rs.getString("description"),
//                        rs.getString("photo"),
//                        rs.getInt("idCategorie"),
//                        rs.getInt("idUser"),
//                        rs.getInt("price"));
//                Cart = a;
//            }
//        } catch (SQLException e) {
//            e.getMessage();
//            e.printStackTrace();
//        }
//        if (Cart == null)
//            throw new RuntimeException("Get: Cart with " + id + " not found");
//        return Cart;
//    }
//
//    //insert Cart
//    public void insertCart(Cart Cart) throws SQLException {
//        String sql = "insert into Cart "
//                + " (label,marque, description,photo,idCategorie,idUser,price)" + " values (?,?,?,?,?,?,?)";
//        PreparedStatement select = CONNEXION.prepareStatement(sql);
//        select.setString(1, Cart.getLabel());
//        select.setString(2, Cart.getMarque());
//        select.setString(3, Cart.getDescription());
//        select.setString(4, Cart.getPhoto());
//        select.setInt(5, Cart.getIdCategorie());
//        select.setInt(6, Cart.getIdUser());
//        select.setInt(7, Cart.getPrice());
//        select.executeUpdate();
//    }
//}
