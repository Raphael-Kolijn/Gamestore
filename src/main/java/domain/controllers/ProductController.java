package domain.controllers;

import domain.controllers.response.Response;
import domain.models.Product;
import domain.services.ProductService;

import javax.ejb.EJB;
import javax.ws.rs.*;

@Path("product")
public class ProductController {

    @EJB
    private ProductService service;

    @GET
    @Produces("application/json")
    public Response getAll() {
        return new Response(true, service.getAll());
    }

    @GET
    @Path("/{id}")
    @Produces("application/json")
    public Response getById(@PathParam("id") Long id) {
        Product product = service.getById(id);
        boolean success = product != null;

        return new Response(success, product);
    }

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public Response create(Product product) {
        boolean success = service.create(product);

        return new Response(success);
    }

    @PUT
    @Consumes("application/json")
    @Produces("application/json")
    public Response update(Product product) {
        boolean success = service.update(product);

        return new Response(success);
    }

    @DELETE
    @Path("/{id}")
    @Consumes("application/json")
    @Produces("application/json")
    public Response delete(@PathParam("id") Long id) {
        boolean success = service.delete(id);

        return new Response(success);
    }
}
