/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package equipo2_crudapp_server.service;

import equipo2_crudapp_server.entities.User;
import java.util.HashSet;
import java.util.Set;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Enterprise Java Bean that contains all the logic for the entity User.
 *
 * @author Diego Corral
 */
@Stateless
public class EJBUser implements EJBUserInterface {

    @PersistenceContext(unitName = "Equipo2_CRUDapp_ServerPU")
    private EntityManager entityManager;

    /**
     * Creates a new user in the database
     *
     * @param user User to create
     */
    @Override
    public void createUser(User user) {
        entityManager.persist(user);
    }

    /**
     * Updates an existing user in the database
     *
     * @param user User that is going to be modified with the new values
     */
    @Override
    public void modifyUser(User user) {
        entityManager.merge(user);
    }

    /**
     * Deletes an specified user
     *
     * @param user User to delete
     */
    @Override
    public void deleteUser(User user) {
        entityManager.remove(entityManager.merge(user));
    }

    /**
     * Search for an specified user in the database
     *
     * @param userId Id of the user to search
     * @return The user found
     */
    @Override
    public User findUser(Integer userId) {
        return entityManager.find(User.class, userId);
    }

    /**
     * Method that searches for a user with the specified email
     *
     * @param email Email of the user to find
     * @return The user found
     */
    @Override
    public User findUserByEmail(String email) {
        return (User) entityManager.createNamedQuery("findUserByEmail").setParameter("email", email).getSingleResult();
    }

    /**
     * This function finds all users in the database and returns them.
     *
     * @return List of type User with all the users in the database.
     */
    @Override
    public Set<User> findAllUsers() {
        return new HashSet<>(entityManager.createNamedQuery("findAllUsers").getResultList());
    }

    /**
     * Method to check the credentials of a user.
     *
     * @param login Login of the user.
     * @param password Password of the user.
     * @return The user, if found.
     */
    @Override
    public User checkUserPassword(String login, String password) {
        return (User) entityManager.createNamedQuery("checkUserPassword").setParameter("login", login).setParameter("password", password).getSingleResult();
    }
    
    /**
     * Method that searches for a user with the specified login
     *
     * @param login Login of the user to find
     * @return The user found
     */
    @Override
    public User findUserByLogin(String login) {
        return (User) entityManager.createNamedQuery("findUserByLogin").setParameter("login", login).getSingleResult();
    }
}
