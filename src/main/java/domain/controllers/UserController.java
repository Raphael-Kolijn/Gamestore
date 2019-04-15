package domain.controllers;



import domain.models.*;
import domain.services.*;

import javax.ejb.*;
import javax.ws.rs.*;
import java.util.*;

@Path("user")
public class UserController {

    @EJB
    UserService userService;

    @GET
    @Consumes("application/json")
    public List<User> All(){
        return userService.All();
    }

    @POST
    @Consumes("application/json")
    public void create(User user){
        userService.create(user);
    }

    @GET
    @Path("/{id}")
    @Consumes("application/json")
    public User getById(@PathParam("id") Long id){
        return userService.getById(id);
    }

    @PUT
    @Consumes("application/json")
    public void update(User user){
        userService.update(user);
    }

    @DELETE
    @Path("/{id}")
    @Consumes("application/json")
    public void delete(@PathParam("id") Long id){
        userService.delete(id);
    }
}
