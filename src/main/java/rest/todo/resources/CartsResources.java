package rest.todo.resources;

import rest.todo.dao.ArticleDao;
import rest.todo.dao.CartDao;
import rest.todo.model.Article;
import rest.todo.model.Cart;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@Path("/cart")
public class CartsResources {
    CartDao cartDao = new
            CartDao();
    ArticleDao articleDao = new ArticleDao();

    @Context
    UriInfo uriInfo;
    @Context
    Request request;
    @GET
    @Path("listproduct/{idUser}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Article> getArticlesOfCart(@PathParam("idUser") int idUser) {
        List<Article> articleList = cartDao.getAllProductOfCart(idUser);
        return articleList;
    }

    //Insertion de produit dans le panier
    @GET
    @Path("/add/{idProduct}/{idUser}")
    public boolean newArticle(@PathParam("idProduct") int idProd,@PathParam("idUser") int idUser,
                           @Context HttpServletResponse servletResponse) throws IOException, SQLException {
        Cart cart = new Cart(idUser,idProd);
       return  cartDao.insertIntoCart(cart);
    }

    @GET
    @Path("/delete/{idProduct}/{idUser}")
    public boolean deleteArticleFromCart(@PathParam("idProduct") int id,@PathParam("idUser") int idUser) throws  SQLException{
        return cartDao.deleteFromCart(id,idUser);
    }
    @GET
    @Path("/deleteAll/{idUser}")
    public boolean deleteArticleFromCartByIdUser(@PathParam("idUser") int id) throws SQLException{
        return cartDao.deleteFromCartByIdUser(id);
    }
    // Defines that the next path parameter after todos is
    // treated as a parameter and passed to the TodoResources
    // Allows to type http://localhost:8080/rest.todo/rest/todos/1
    // 1 will be treaded as parameter todo and passed to TodoResource
    @Path("{idcart}")
    public CartResource getArticle(@PathParam("idcart") int id) {
        return new CartResource(uriInfo, request, id);

    }
    @GET
    @Path("/totalPrice/{idUser}")
    public Integer totalPrice(@PathParam("idUser") int idUser) {
        return cartDao.totalPrice(idUser);
    }
}

