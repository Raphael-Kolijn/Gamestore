package domain.controllers;

import domain.controllers.response.Response;
import domain.models.Review;
import domain.services.ReviewService;

import javax.ejb.EJB;
import javax.ws.rs.*;

@Path("review")
public class ReviewController {

    @EJB
    private ReviewService service;

    @GET
    @Produces("application/json")
    public Response getAll() {
        return new Response(true, service.getAll());
    }

    @GET
    @Path("/{id}")
    @Produces("application/json")
    public Response getById(@PathParam("id") Long id) {
        Review review = service.getById(id);
        boolean success = review != null;

        return new Response(success, review);
    }

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public Response create(Review review) {
        boolean success = service.create(review);

        return new Response(success);
    }

    @PUT
    @Consumes("application/json")
    @Produces("application/json")
    public Response update(Review review) {
        boolean success = service.update(review);

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
