package rest.todo.resources;

import rest.todo.ConnectionDB;
import rest.todo.dao.ArticleDao;
import rest.todo.dao.TodoDao;
import rest.todo.model.Article;
import rest.todo.model.Todo;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import javax.xml.bind.JAXBElement;
import java.sql.*;
import java.util.List;

public class ArticleResource {
    @Context
    UriInfo uriInfo;
    @Context
    Request request;
    int id;

    ArticleDao articleDao = new ArticleDao();
    public ArticleResource(UriInfo uriInfo, Request request, int id) {
        this.uriInfo = uriInfo;
        this.request = request;
        this.id = id;
    }

    //Application integration
    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public String getArticle() {
        Article a = articleDao.getArticle(uriInfo,request,id) ;
        return a.toString();
    }

    // for the browser
    @GET
    @Produces(MediaType.TEXT_XML)
    public String getArticleHTML() {
        Article a = articleDao.getArticle(uriInfo,request,id) ;
        return a.toString();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_XML)
    public Response putTodo(JAXBElement<Todo> todo) {
        Todo c = todo.getValue();
        return putAndGetResponse(c);
    }

    @DELETE
    public void deleteTodo() {
        Todo c = TodoDao.instance.getModel().remove(id);
        if (c == null)
            throw new RuntimeException("Delete: Todo with " + id + " not found");
    }

    private Response putAndGetResponse(Todo todo) {
        Response res;
        if (TodoDao.instance.getModel().containsKey(todo.getId())) {
            res = Response.noContent().build();
        } else {
            res = Response.created(uriInfo.getAbsolutePath()).build();
        }
        TodoDao.instance.getModel().put(todo.getId(), todo);
        return res;
    }


}
