/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package equipo2_crudapp_server.service;

import equipo2_crudapp_server.entities.User;
import java.util.Set;
import javax.ejb.Local;

/**
 * Interface implemented by EJBUser.
 * 
 * @author Diego Corral
 */
@Local
public interface EJBUserInterface {
    
    /**
     * Creates a new user in the database
     * @param user User to create
     */
    public void createUser(User user);
    
    /**
     * Updates an existing user in the database
     * @param user User that is going to be modified with the new values
     */
    public void modifyUser(User user);
    
    /**
     * Deletes an specified user
     * @param userId Id of the user to delete
     */
    public void deleteUser(Integer userId);
    
    /**
     * Search for an specified user in the database
     * @param userId Id of the user to search
     * @return The user found
     */
    public User findUser(Integer userId);

    /**
     * Method that searches for a user with the specified email
     *
     * @param email Email of the user to find
     * @return The user found
     */
    public User findUserByEmail(String email);
    
    /**
     * Finds and returns a list containing all the users from the database.
     *
     * @return List of type User with all the users found.
     */
    public Set<User> findAllUsers();
    
    /**
     * Method to check the credentials of a user.
     * 
     * @param login Login of the user.
     * @param password Password of the user.
     * @return The user, if found.
     */
    public User checkUserPassword(String login, String password);
}
