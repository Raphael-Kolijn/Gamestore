package domain.controllers;

import domain.controllers.response.Response;
import domain.models.Company;
import domain.services.CompanyService;

import javax.ejb.EJB;
import javax.ws.rs.*;

@Path("company")
public class CompanyController {

    @EJB
    private CompanyService service;

    @GET
    @Produces("application/json")
    public Response getAll() {
        return new Response(true, service.getAll());
    }

    @GET
    @Path("/{id}")
    @Produces("application/json")
    public Response getById(@PathParam("id") Long id) {
        Company company = service.getById(id);
        boolean success = company != null;

        return new Response(success, company);
    }

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public Response create(Company company) {
        boolean success = service.create(company);

        return new Response(success);
    }

    @PUT
    @Consumes("application/json")
    @Produces("application/json")
    public Response update(Company company) {
        boolean success = service.update(company);

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
