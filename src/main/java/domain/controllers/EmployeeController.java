package domain.controllers;

import domain.controllers.response.Response;
import domain.models.Employee;
import domain.services.EmployeeService;

import javax.ejb.EJB;
import javax.ws.rs.*;

@Path("employee")
public class EmployeeController {

    @EJB
    private EmployeeService service;

    @GET
    @Produces("application/json")
    public Response getAll() {
        return new Response(true, service.getAll());
    }

    @GET
    @Path("/{id}")
    @Produces("application/json")
    public Response getById(@PathParam("id") Long id) {
        Employee employee = service.getById(id);
        boolean success = employee != null;

        return new Response(success, employee);
    }

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public Response create(Employee employee) {
        boolean success = service.create(employee);

        return new Response(success);
    }

    @PUT
    @Consumes("application/json")
    @Produces("application/json")
    public Response update(Employee employee) {
        boolean success = service.update(employee);

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
