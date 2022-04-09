package rest.todo.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="ApplicationConstant")
public class Cart {
    private int id;
    private int idUser;
    private int idProduct;
    private int quantity;

    public Cart(){

    }
    public Cart( int idUser, int idProduct) {
        this.idUser = idUser;
        this.idProduct = idProduct;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdUser() {
        return this.idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getIdProduct() {
        return this.idProduct;
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
