package domain.controllers;
import domain.models.*;
import domain.services.ReviewService;
import javax.ws.rs.core.Response;
import javax.ejb.EJB;
import javax.ws.rs.*;

import static org.apache.openejb.persistence.PersistenceBootstrap.logger;

@Path("review")
public class ReviewController {

    @EJB
    private ReviewService service;

    @GET
    @Produces("application/json")
    public Response getAll() {
        try {
            return Response.ok(service.getAll()).build();
        } catch (Exception e) {
            logger.severe(e.getMessage());
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @GET
    @Path("/{id}")
    @Produces("application/json")
    public Response getById(@PathParam("id") Long id) {
        Review review = service.getById(id);
        try {
            if (review == null)
                return Response.status(Response.Status.NOT_FOUND).build();

            return Response.ok(review).build();
        } catch (Exception e) {
            logger.severe(e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public Response create(Review review) {
        try {
            service.create(review);
            return Response.status(Response.Status.CREATED).build();
        } catch (Exception e) {
            logger.severe(e.getMessage());
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @PUT
    @Consumes("application/json")
    @Produces("application/json")
    public Response update(Long id) {
        try {
            Review review = service.getById(id);
            if (review == null)
                return Response.status(Response.Status.NOT_FOUND).build();

            service.update(review);

            return Response.status(Response.Status.OK).build();
        } catch (Exception ex) {
            logger.severe(ex.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @DELETE
    @Path("/{id}")
    @Consumes("application/json")
    @Produces("application/json")
    public Response delete(@PathParam("id") Long id) {
        try {
            service.delete(id);
            return Response.status(Response.Status.OK).build();
        } catch (Exception e) {
            logger.severe(e.getMessage());
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
