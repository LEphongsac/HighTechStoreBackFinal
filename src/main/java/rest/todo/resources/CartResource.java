package rest.todo.resources;

import rest.todo.dao.ArticleDao;
import rest.todo.dao.CartDao;
import rest.todo.dao.TodoDao;
import rest.todo.model.Article;
import rest.todo.model.Cart;
import rest.todo.model.Todo;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Produces;
import javax.ws.rs.core.*;
import javax.xml.bind.JAXBElement;
import java.util.List;

public class CartResource {

    @Context
    UriInfo uriInfo;
    @Context
    Request request;
    int id;

    CartDao cartDao = new CartDao();
    public CartResource(UriInfo uriInfo, Request request, int id) {
        this.uriInfo = uriInfo;
        this.request = request;
        this.id = id;
    }

    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public String getCart() {
        List<Article> a = cartDao.getAllProductOfCart(this.id) ;
        return a.toString();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_XML)
    public Response putArticle(JAXBElement<Article> article) {
        Article c = article.getValue();
        return putAndGetResponse(c);
    }

    private Response putAndGetResponse(Article article) {
        Response res = null;
        return res;
    }




}
