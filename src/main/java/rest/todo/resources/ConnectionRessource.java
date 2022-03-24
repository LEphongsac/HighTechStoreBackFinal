package rest.todo.resources;

import rest.todo.ConnectionDB;
import rest.todo.dao.TodoDao;
import rest.todo.model.Todo;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

@Path("/connexion")
public class ConnectionRessource {
    @Context
    UriInfo uriInfo;
    @Context
    Request request;
    @GET
    @Produces(MediaType.TEXT_XML)
    public String getTodosBrowser() {
        ConnectionDB con = new ConnectionDB();
        Connection essai = con.getDBConnection();
        if(essai!=null) return "reussi";
        return "erreur";
    }

}
