package rest.todo.resources;

import rest.todo.dao.ArticleDao;
import rest.todo.model.Article;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class CartsResources {
    ArticleDao articleDao = new ArticleDao();

    @Context
    UriInfo uriInfo;
    @Context
    Request request;

//    @GET
//    @Path("list")
//    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
//    public List<Article> getArticles() {
//        List<Article> articleList = articleDao.getAllArticle();
//        return articleList;
//    }

    //Insertion
    @GET
    @Path("/add/{label}/{marque}/{description}/{photo}/{idCategorie}/{idUser}/{price}")
    @Produces(MediaType.TEXT_HTML)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public void newArticle(@PathParam("label") String label,
                           @PathParam("marque") String marque,
                           @PathParam("description") String description,
                           @PathParam("photo") String photo,
                           @PathParam("idCategorie") int idCategorie,
                           @PathParam("idUser") int idUser,
                           @PathParam("price") int price,
                           @Context HttpServletResponse servletResponse) throws IOException, SQLException {
        Article article = new Article(label,marque, description,photo,idCategorie,idUser,price);
        articleDao.insertArticle(article);
    }

    // Defines that the next path parameter after todos is
    // treated as a parameter and passed to the TodoResources
    // Allows to type http://localhost:8080/rest.todo/rest/todos/1
    // 1 will be treaded as parameter todo and passed to TodoResource
    @Path("{idarticle}")
    public ArticleResource getArticle(@PathParam("idarticle") int id) {
        return new ArticleResource(uriInfo, request, id);
    }
}