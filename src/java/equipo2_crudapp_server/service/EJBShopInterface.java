/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package equipo2_crudapp_server.service;

import equipo2_crudapp_server.entities.Shop;
import java.util.List;
import javax.ejb.Local;

/**
 * Interface implemented by EJBShop.
 * 
 * @author Diego Corral
 */
@Local
public interface EJBShopInterface {
    
    /**
     * Creates a new shop in the database
     * @param shop Shop to create
     */
    public void createShop(Shop shop);

    /**
     * Updates an existing user in the database
     * @param shop Shop that is going to be modified with the new values
     */
    public void modifyShop(Shop shop);

    /**
     * Deletes an specified shop
     * @param shopId Id of the shop to delete
     */
    public void deleteShop(Integer shopId);

    /**
     * Search for an specified shop in the database
     * @param shopId Id of the shop to search
     * @return The shop found
     */
    public Shop findShop(Integer shopId);

    /**
     * Finds and returns a list containing all the shops from the database.
     *
     * @return List of type Shop with all the shops found.
     */
    public List<Shop> findAllShops();
}
