package rest.todo.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="ApplicationConstant")
public class Categorie {
    private int idCategorie;
    private String label;

    public Categorie(){

    }
    public Categorie(int idCategorie, String label) {
        this.idCategorie = idCategorie;
        this.label = label;
    }

    public int getIdCategorie() {
        return idCategorie;
    }

    public void setIdCategorie(int idCategorie) {
        this.idCategorie = idCategorie;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
