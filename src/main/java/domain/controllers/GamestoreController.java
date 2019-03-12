package domain.controllers;

import domain.controllers.response.Response;
import domain.models.Gamestore;
import domain.services.GamestoreService;

import javax.ejb.EJB;
import javax.ws.rs.*;

@Path("gamestore")
public class GamestoreController {

    @EJB
    private GamestoreService service;

    @GET
    @Produces("application/json")
    public Response getAll() {
        return new Response(true, service.getAll());
    }

    @GET
    @Path("/{id}")
    @Produces("application/json")
    public Response getById(@PathParam("id") Long id) {
        Gamestore gamestore = service.getById(id);
        boolean success = gamestore != null;

        return new Response(success, gamestore);
    }

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public Response create(Gamestore gamestore) {
        boolean success = service.create(gamestore);

        return new Response(success);
    }

    @PUT
    @Consumes("application/json")
    @Produces("application/json")
    public Response update(Gamestore gamestore) {
        boolean success = service.update(gamestore);

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
