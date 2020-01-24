/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package equipo2_crudapp_server.service;

import equipo2_crudapp_server.entities.Wish;
import java.util.Set;
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
 * RESTful service provider for the entity Wish.
 * 
 * @author Adrián García
 */
@Path("wish")
public class WishREST {
    
    /**
     * Enterprise Java Beans for the entity wish
     */
    @EJB
    private EJBWishInterface ejbWish;

    /**
     * Method that inserts a wish in the database
     * @param wish The wish that is going to be inserted
     */
    @POST
    @Consumes({MediaType.APPLICATION_XML})
    public void createWish(Wish wish) {
        ejbWish.createWish(wish);
    }

    /**
     * Method that modifies a wish in the database
     * @param wishId Id of the wish
     * @param wish The wish that is going to be modified and its new values
     */
    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML})
    public void modifyWish(@PathParam("id") Integer wishId, Wish wish) {
        ejbWish.modifyWish(wish);
    }
    
    /**
     * Method that deletes a wish from the database
     * @param wishId Id of the wish to delete
     */
    @DELETE
    @Path("{id}")
    public void removeWish(@PathParam("id") Integer wishId) {
        ejbWish.deleteWish(ejbWish.findWish(wishId));
    }

    /**
     * Method that finds a wish
     * @param wishId Id of the wish to search
     * @return The found wish
     */
    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML})
    public Wish findWish(@PathParam("id") Integer wishId) {
        return ejbWish.findWish(wishId);
    }
        
    /**
     * Finds and returns a list containing all the wishes from the database.
     *
     * @return List of type Wish with all the wishes found.
     */
    @GET
    @Produces({MediaType.APPLICATION_XML})
    public Set<Wish> findAllWishes() {
        return ejbWish.findAllWishes();
    }
}
