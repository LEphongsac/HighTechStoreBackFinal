package rest.todo.resources;

import rest.todo.dao.ArticleDao;
import rest.todo.model.Article;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.core.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@Path("/articles")
public class ArticlesResources {
    ArticleDao articleDao = new ArticleDao();

    @Context
    UriInfo uriInfo;
    @Context
    Request request;

    //list article
    @GET
    @Path("/all}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response getAllArticles() {
        List<Article> articleList = articleDao.getAllArticle();
        return Response.ok(articleList)
                .header("Access-Control-Allow-Origin", "*")
                .build();
    }

    //list article selon categorie
    @GET
    @Path("list/{idCategorie}")
    @Produces({ MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    public List<Article> getArticles(@PathParam("idCategorie") int idCategorie) {
        List<Article> articleList = articleDao.getAllArticleByCategorie(idCategorie);
        return articleList;
    }
    @GET
    @Path("/update/{idArticle}/{label}/{marque}/{description}/{idCategorie}/{idUser}/{price}")
    @Produces({ MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    public Article UpdateArticle( @PathParam("idArticle") int idArticle,
                                  @PathParam("label") String label,
                                  @PathParam("marque") String marque,
                                  @PathParam("description") String description,
                                  @PathParam("idCategorie") int idCategorie,
                                  @PathParam("idUser") int idUser,
                                  @PathParam("price") int price) throws SQLException {
        Article article = new Article(label,marque,description,"",idCategorie,idUser,price);
        return articleDao.updateArticle(article,idArticle);
    }

    //Insertion
    @GET
    @Path("/add/{label}/{marque}/{description}/{idCategorie}/{idUser}/{price}")
    public Boolean newArticle(@PathParam("label") String label,
                        @PathParam("marque") String marque,
                        @PathParam("description") String description,
                        @PathParam("idCategorie") int idCategorie,
                        @PathParam("idUser") int idUser,
                        @PathParam("price") int price,
                        @Context HttpServletResponse servletResponse) throws IOException, SQLException {
        Article article = new Article(label,marque, description,"",idCategorie,idUser,price);
       return  articleDao.insertArticle(article);
    }

    // Defines that the next path parameter after todos is
    // treated as a parameter and passed to the TodoResources
    // Allows to type http://localhost:8080/rest.todo/rest/todos/1
    // 1 will be treaded as parameter todo and passed to TodoResource
    @Path("{idarticle}")
    public ArticleResource getArticle(@PathParam("idarticle") int id) {
        return new ArticleResource(uriInfo, request, id);
    }
    @GET
    @Path("/delete/{idArticle}")
    public boolean deleteArticle(@PathParam("idArticle") int idArticle) throws  SQLException{
        return  articleDao.deleteArticle(idArticle);
    }
}
