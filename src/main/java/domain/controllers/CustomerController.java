package domain.controllers;
import domain.models.Customer;
import domain.services.CustomerService;
import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

import static org.apache.openejb.persistence.PersistenceBootstrap.logger;

@Path("customer")
public class CustomerController {

    @EJB
    private CustomerService service;

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
        Customer customer = service.getById(id);
        try {
            if (customer == null)
                return Response.status(Response.Status.NOT_FOUND).build();

            return Response.ok(customer).build();
        } catch (Exception e) {
            logger.severe(e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public Response create(Customer customer) {
        try {
            service.create(customer);
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
            Customer customer = service.getById(id);

            if (customer == null)
                return Response.status(Response.Status.NOT_FOUND).build();

            service.update(customer);

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
