package rest.todo.resources;


import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;

import rest.todo.ConnectionDB;
import rest.todo.dao.ArticleDao;
import rest.todo.dao.TodoDao;
import rest.todo.model.Article;
import rest.todo.model.Todo;


/// Will map the resource to the URL todos
@Path("/todos")
public class TodosResource {

    // Allows to insert contextual objects into the class,
    // e.g. ServletContext, Request, Response, UriInfo
    @Context
    UriInfo uriInfo;
    @Context
    Request request;


   /*public List<Todo> getTodosBrowser() {
        List<Todo> todos = new ArrayList<Todo>();
        todos.addAll(TodoDao.instance.getModel().values());
        return todos;
    }*/

    // Return the list of todos for applications
    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Todo> getTodos() {
        List<Todo> todos = new ArrayList<Todo>();
        todos.addAll(TodoDao.instance.getModel().values());
        return todos;
    }

    // returns the number of todos
    // Use http://localhost:8080/com.vogella.jersey.todo/rest/todos/count
    // to get the total number of records
    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public int getCount() {
        Connection connection = ConnectionDB.getDBConnection();
        Statement statement;
        int id = 0;
        try {
            statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT id, label FROM article");
            // Extract data from result set
            while (rs.next()) {
                // Retrieve by column name
                id = rs.getInt("ID");
            }
        } catch (SQLException e) {
            e.getMessage();
            e.printStackTrace();
        }
        return id;
    }

    @POST
    @Produces(MediaType.TEXT_HTML)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public void newTodo(@FormParam("id") String id,
                        @FormParam("summary") String summary,
                        @FormParam("description") String description,
                        @Context HttpServletResponse servletResponse) throws IOException {
        Todo todo = new Todo(id, summary);
        if (description != null) {
            todo.setDescription(description);
        }
        TodoDao.instance.getModel().put(id, todo);

        servletResponse.sendRedirect("../create_todo.html");
    }

    // Defines that the next path parameter after todos is
    // treated as a parameter and passed to the TodoResources
    // Allows to type http://localhost:8080/rest.todo/rest/todos/1
    // 1 will be treaded as parameter todo and passed to TodoResource
    @Path("{todo}")
    public TodoResource getTodo(@PathParam("todo") String id) {
        return new TodoResource(uriInfo, request, id);
    }

}