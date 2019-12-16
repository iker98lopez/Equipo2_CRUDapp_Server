/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package equipo2_crudapp_server.service;

import equipo2_crudapp_server.entities.User;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Diego Corral
 */
@Stateless
public class EJBUser implements EJBUserInterface{

    @PersistenceContext(unitName = "Equipo2_CRUDapp_ServerPU")
    private EntityManager entityManager;

    /**
     * Creates a new user in the database
     * @param user User to create
     */
    @Override
    public void createUser(User user) {
        entityManager.persist(user);
    }

    /**
     * Updates an existing user in the database
     * @param user User that is going to be modified with the new values
     */
    @Override
    public void modifyUser(User user) {
        entityManager.merge(user);
    }

    /**
     * Deletes an specified user
     * @param userId Id of the user to delete
     */
    @Override
    public void deleteUser(Integer userId) {
        entityManager.remove(entityManager.merge(userId));
    }

    /**
     * Search for an specified user in the database
     * @param userId Id of the user to search
     * @return The user found
     */
    @Override
    public User findUser(Integer userId) {
        return entityManager.find(User.class, userId);
    }
    
}
