package domain.controllers;

import domain.controllers.response.Response;
import domain.models.Customer;
import domain.services.CustomerService;

import javax.ejb.EJB;
import javax.ws.rs.*;

@Path("customer")
public class CustomerController {

    @EJB
    private CustomerService service;

    @GET
    @Produces("application/json")
    public Response getAll() {
        return new Response(true, service.getAll());
    }

    @GET
    @Path("/{id}")
    @Produces("application/json")
    public Response getById(@PathParam("id") Long id) {
        Customer customer = service.getById(id);
        boolean success = customer != null;

        return new Response(success, customer);
    }

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public Response create(Customer customer) {
        boolean success = service.create(customer);

        return new Response(success);
    }

    @PUT
    @Consumes("application/json")
    @Produces("application/json")
    public Response update(Customer customer) {
        boolean success = service.update(customer);

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
