/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package equipo2_crudapp_server.service;

import equipo2_crudapp_server.entities.Wish;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Enterprise Java Bean that contains all the logic for the entity Wish.
 * 
 * @author Adrián García
 */
@Stateless
public class EJBWish implements EJBWishInterface {
    
    @PersistenceContext(unitName = "Equipo2_CRUDapp_ServerPU")
    private EntityManager entityManager;

    /**
     * This function creates a new entry in the database with the given wish.
     * 
     * @param wish New wish to be added.
     */
    @Override
    public void createWish(Wish wish) {
        entityManager.persist(wish);
    }

    /**
     * This function updates an existing wish with the data from the given 
     * wish.
     * 
     * @param wish Wish with the modifications.
     */
    @Override
    public void modifyWish(Wish wish) {
        entityManager.merge(wish);
        entityManager.flush();
    }

    /**
     * This function deletes an existing wish. It is found by the given ID.
     * 
     * @param wishId ID of the wish to be deleted.
     */
    @Override
    public void deleteWish(Integer wishId) {
        entityManager.remove(entityManager.merge(wishId));
    }

    /**
     * This function finds a wish by the id it receives and returns it.
     * 
     * @param id ID of the wish.
     * @return Wish, if found.
     */
    @Override
    public Wish findWish(Integer id) {
        return entityManager.find(Wish.class, id);
    }
    
    /**
     * This function finds all wishes in the database and returns them.
     *
     * @return List of type Wish with all the wishes in the database.
     */
    @Override
    public List<Wish> findAllWishes() {
        return entityManager.createNamedQuery("findAllWishes").getResultList();
    }
}
