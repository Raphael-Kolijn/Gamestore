package domain.controllers;

import domain.controllers.response.Response;
import domain.models.Call;
import domain.repositories.CallRepository;
import domain.services.CallService;

import javax.ejb.EJB;
import javax.ws.rs.*;

@Path("call")
public class CallController {

    @EJB
    private CallService service;

    @GET
    @Produces("application/json")
    public Response getAll() {
        return new Response(true, service.getAll());
    }

    @GET
    @Path("/{id}")
    @Produces("application/json")
    public Response getById(@PathParam("id") Long id) {
        Call call = service.getById(id);
        boolean success = call != null;

        return new Response(success, call);
    }

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public Response create(Call call) {
        boolean success = service.create(call);

        return new Response(success);
    }

    @PUT
    @Consumes("application/json")
    @Produces("application/json")
    public Response update(Call call) {
        boolean success = service.update(call);

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
