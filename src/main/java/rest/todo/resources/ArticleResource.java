package rest.todo.resources;

import rest.todo.dao.ArticleDao;
import rest.todo.dao.TodoDao;
import rest.todo.model.Article;
import rest.todo.model.Todo;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import javax.xml.bind.JAXBElement;
import java.sql.SQLException;

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
    public Article getArticle() {
        Article a = articleDao.getArticle(id) ;
        return a;
    }

    // for the browser
    @GET
    @Produces(MediaType.TEXT_XML)
    public String getArticleHTML() {
        Article a = articleDao.getArticle(id) ;
        return a.toString();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_XML)
    public Response putTodo(JAXBElement<Todo> todo) {
        Todo c = todo.getValue();
        return putAndGetResponse(c);
    }

    @DELETE
    public void deleteArticle() throws SQLException {
        articleDao.deleteArticle(id);
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
