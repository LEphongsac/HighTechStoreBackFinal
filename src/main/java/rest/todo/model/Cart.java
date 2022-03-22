package rest.todo.model;

public class Cart {
    private int id;
    private int idUser;
    private int idProduct;
    private int quantity;

    public Cart(int id, int idUser, int idProduct, int quantity) {
        this.id = id;
        this.idUser = idUser;
        this.idProduct = idProduct;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
