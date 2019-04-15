package domain.controllers;
import domain.models.*;
import domain.services.*;
import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

import static org.apache.openejb.persistence.PersistenceBootstrap.logger;

@Path("game")
public class GameController {

    @EJB
    private GameService service;

    @GET
    //@JWTTokenNeeded
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
        Game game = service.getById(id);
        try {
            if (game == null)
                return Response.status(Response.Status.NOT_FOUND).build();

            return Response.ok(game).build();
        } catch (Exception e) {
            logger.severe(e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public Response create(Game game) {
        try {
            service.create(game);
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
            Game game = service.getById(id);

            if (game == null)
                return Response.status(Response.Status.NOT_FOUND).build();

            service.update(game);

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
