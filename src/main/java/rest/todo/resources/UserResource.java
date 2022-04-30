package rest.todo.resources;//package rest.todo.resources;

//import rest.todo.dao.UserDao;
//import rest.todo.model.User;

import rest.todo.dao.UserDao;
import rest.todo.model.User;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;
import java.sql.SQLException;

public class UserResource {
    @Context
    UriInfo uriInfo;
    @Context
    Request request;
    int id;

    UserDao userDao = new UserDao();
    public UserResource(UriInfo uriInfo, Request request, int id) {
        this.uriInfo = uriInfo;
        this.request = request;
        this.id = id;
    }

    //Application integration
    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public User getUser() {
        User a = userDao.getUserById(id) ;
        return a;
    }

    // for the browser
    @GET
    @Produces(MediaType.TEXT_XML)
    public String getUserHTML() {
        User a = userDao.getUserById(id) ;
        return a.toString();
    }

    @DELETE
    public void deleteUser() throws SQLException {
        userDao.deleteUser(id);
    }
}
