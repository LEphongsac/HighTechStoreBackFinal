package rest.todo.resources;

import rest.todo.dao.ArticleDao;
import rest.todo.dao.UserDao;
import rest.todo.model.Article;
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
public class UserRessource {

    UserDao userDao = new UserDao();
    @GET
    @Path("/login/{email}/{password}")
    @Produces({ MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    public User login(@PathParam("email") String email , @PathParam("password") String password) {
        if (userDao.login(email,password) == true){
            return userDao.findUserByEmail(email);
        }
        return null;
    }
}
