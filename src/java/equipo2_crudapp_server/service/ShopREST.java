/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package equipo2_crudapp_server.service;

import equipo2_crudapp_server.entities.Shop;
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
@Path("shop")
public class ShopREST {
    
    /**
     * Enterprise Java Beans for the entity User
     */
    @EJB
    private EJBShopInterface ejbShop;

    /**
     * Method that inserts a shop in the database
     * @param shop The shop that is going to be inserted
     */
    @POST
    @Consumes({MediaType.APPLICATION_XML})
    public void createShop(Shop shop) {
        ejbShop.createShop(shop);
    }

    /**
     * Method that modifies a shop in the database
     * @param shopId Id of the shop
     * @param shop The shop that is going to be modified and its new values
     */
    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML})
    public void modifyShop(@PathParam("id") Integer shopId, Shop shop) {
        ejbShop.modifyShop(shop);
    }
    
    /**
     * Method that deletes a shop from the database
     * @param shopId Id of the shop to delete
     */
    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer shopId) {
        ejbShop.deleteShop(shopId);
    }

    /**
     * Method that finds a shop 
     * @param shopId Id of the shop to search
     * @return The found shop
     */
    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML})
    public Shop find(@PathParam("id") Integer shopId) {
        return ejbShop.findShop(shopId);
    }
}
