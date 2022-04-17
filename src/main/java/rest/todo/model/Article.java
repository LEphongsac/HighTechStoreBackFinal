package rest.todo.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="ApplicationConstant")
public class Article {
    private int id;
    private String label;
    private String marque;
    private String description;
    private String photo;
    private int idCategorie;
    private int idUser;
    private int price;

    public Article() {
    }

    public Article(int id, String label, String marque, String description, String photo, int idCategorie, int idUser, int price) {
        this.id = id;
        this.label = label;
        this.marque = marque;
        this.description = description;
        this.photo = photo;
        this.idCategorie = idCategorie;
        this.idUser = idUser;
        this.price = price;
    }

    public Article(String label, String marque, String description, String photo, int idCategorie, int idUser, int price) {
        this.label = label;
        this.marque = marque;
        this.description = description;
        this.photo = photo;
        this.idCategorie = idCategorie;
        this.idUser = idUser;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public int getIdCategorie() {
        return idCategorie;
    }

    public void setIdCategorie(int idCategorie) {
        this.idCategorie = idCategorie;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
