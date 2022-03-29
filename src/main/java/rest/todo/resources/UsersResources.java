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

@Path("/articles")
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
    public List<User> getAllArticles() {
        List<User> userList = userDao.getAllUser();
        return userList;
    }


    @GET
    @Path("/login/{username}/{password}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public int getRoleUser(@PathParam("username") String username, @PathParam("password") String password){
        return userDao.authentication(username,password);
    }

    @Path("{iduser}")
    public ArticleResource getArticle(@PathParam("iduser") int id) {
        return new ArticleResource(uriInfo, request, id);
    }
}
