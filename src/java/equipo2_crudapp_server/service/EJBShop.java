/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package equipo2_crudapp_server.service;

import equipo2_crudapp_server.entities.Shop;
import java.util.HashSet;
import java.util.Set;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Enterprise Java Bean that contains all the logic for the entity Shop.
 * 
 * @author Diego Corral
 */
@Stateless
public class EJBShop implements EJBShopInterface{

    @PersistenceContext(unitName = "Equipo2_CRUDapp_ServerPU")
    private EntityManager entityManager;

    /**
     * Creates a new shop in the database
     * @param shop Shop to create
     */
    @Override
    public void createShop(Shop shop) {
        entityManager.persist(shop);
    }

    /**
     * Updates an existing user in the database
     * @param shop Shop that is going to be modified with the new values
     */
    @Override
    public void modifyShop(Shop shop) {
        entityManager.merge(shop);
        entityManager.flush();
    }

    /**
     * Deletes an specified shop
     * @param shopId Id of the shop to delete
     */
    @Override
    public void deleteShop(Integer shopId) {
        entityManager.remove(entityManager.merge(shopId));
    }

    /**
     * Search for an specified shop in the database
     * @param id Id of the shop to search
     * @return The shop found
     */
    @Override
    public Shop findShop(Integer id) {
        return entityManager.find(Shop.class, id);
    }

    /**
     * This function finds all shops in the database and returns them.
     *
     * @return List of type Shop with all the shops in the database.
     */
    @Override
    public Set<Shop> findAllShops() {
        return new HashSet<Shop>(entityManager.createNamedQuery("findAllShops").getResultList());
    }
}
