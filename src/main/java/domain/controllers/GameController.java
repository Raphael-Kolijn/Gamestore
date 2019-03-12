package domain.controllers;

import domain.controllers.response.Response;
import domain.models.Game;
import domain.services.GameService;

import javax.ejb.EJB;
import javax.ws.rs.*;

@Path("game")
public class GameController {

    @EJB
    private GameService service;

    @GET
    @Produces("application/json")
    public Response getAll() {
        return new Response(true, service.getAll());
    }

    @GET
    @Path("/{id}")
    @Produces("application/json")
    public Response getById(@PathParam("id") Long id) {
        Game game = service.getById(id);
        boolean success = game != null;

        return new Response(success, game);
    }

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public Response create(Game game) {
        boolean success = service.create(game);

        return new Response(success);
    }

    @PUT
    @Consumes("application/json")
    @Produces("application/json")
    public Response update(Game game) {
        boolean success = service.update(game);

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
