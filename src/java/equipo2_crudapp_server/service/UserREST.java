/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package equipo2_crudapp_server.service;

import equipo2_crudapp_server.entities.User;
import java.util.List;
import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Diego Corral
 */
@Path("user")
public class UserREST {

    /**
     * Enterprise Java Beans for the entity User
     */
    @EJB
    private EJBUserInterface ejbUser;

    /**
     * Method that creates a new user in the database
     * @param user 
     */
    @POST
    @Consumes({MediaType.APPLICATION_XML})
    public void createUser(User user) {
        ejbUser.createUser(user);
    }

    /**
     * Method that modifies an specified user in the database
     * @param userId Id of the user
     * @param user User that is going to be modified and its new values
     */
    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML})
    public void modifyUser(@PathParam("id") Integer userId, User user) {
        ejbUser.modifyUser(user);
    }

    /**
     * Method that deletes a user from the database
     * @param userId Id of the user that is going to be deleted
     */
    @DELETE
    @Path("{id}")
    public void deleteUser(@PathParam("id") Integer userId) {
        ejbUser.deleteUser(userId);
    }

    /**
     * Method that search for a user
     * @param userId Id of the user to find
     * @return The user found
     */
    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML})
    public User findUser(@PathParam("id") Integer userId) {
        return ejbUser.findUser(userId);
    }
    
    /**
     * Finds and returns a list containing all the users from the database.
     *
     * @return List of type User with all the users found.
     */
    @GET
    @Produces({MediaType.APPLICATION_XML})
    public List<User> findAllUsers() {
        return ejbUser.findAllUsers();
    }
}
