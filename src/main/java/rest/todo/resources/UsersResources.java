package rest.todo.resources;

import rest.todo.dao.UserDao;
import rest.todo.model.User;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;
import java.util.List;

@Path("/user")
public class UsersResources {
    UserDao userDao = new UserDao();

    @Context
    UriInfo uriInfo;
    @Context
    Request request;

    //list article
    @GET
    @Path("/all}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<User> getAllUsers() {
        List<User> userList = userDao.getAllUsers();
        return userList;
    }


    @GET
    @Path("/login/{email}/{password}")
    public boolean login (@PathParam("email") String email, @PathParam("password") String password){
        return userDao.login(email,password);
    }

    @Path("{iduser}")
    public ArticleResource getArticle(@PathParam("iduser") int id) {
        return new ArticleResource(uriInfo, request, id);
    }
}
