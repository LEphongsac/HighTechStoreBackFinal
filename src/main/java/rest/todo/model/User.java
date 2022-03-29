package rest.todo.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="ApplicationConstant")
public class User {
    private int id;
    private String username;
    private String password;
    private String firstname;
    private String lastname;
    private String role;

    public User(){}
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getToken() {
        return role;
    }

    public void setToken(String role) {
        this.role = role;
    }
}
