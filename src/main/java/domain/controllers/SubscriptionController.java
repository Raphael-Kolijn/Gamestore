package domain.controllers;

import domain.controllers.response.Response;
import domain.models.Subscription;
import domain.services.SubscriptionService;

import javax.ejb.EJB;
import javax.ws.rs.*;

@Path("subscription")
public class SubscriptionController {

    @EJB
    private SubscriptionService service;

    @GET
    @Produces("application/json")
    public Response getAll() {
        return new Response(true, service.getAll());
    }

    @GET
    @Path("/{id}")
    @Produces("application/json")
    public Response getById(@PathParam("id") Long id) {
        Subscription subscription = service.getById(id);
        boolean success = subscription != null;

        return new Response(success, subscription);
    }

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public Response create(Subscription subscription) {
        boolean success = service.create(subscription);

        return new Response(success);
    }

    @PUT
    @Consumes("application/json")
    @Produces("application/json")
    public Response update(Subscription subscription) {
        boolean success = service.update(subscription);

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
