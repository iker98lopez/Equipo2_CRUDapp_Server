/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package equipo2_crudapp_server.service;

import equipo2_crudapp_ciphering.CipheringManager;
import equipo2_crudapp_server.email.EmailSender;
import equipo2_crudapp_server.entities.User;
import java.security.SecureRandom;
import java.sql.Date;
import java.time.LocalDate;
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
 * RESTful service provider for the entity User.
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
     *
     * @param user
     */
    @POST
    @Consumes({MediaType.APPLICATION_XML})
    public void createUser(User user) {
        user.setPassword(CipheringManager.hashCipher(new String(CipheringManager.decipherText(user.getPassword().getBytes()))));
        ejbUser.createUser(user);
    }

    /**
     * Method that modifies an specified user in the database
     *
     * @param userId Id of the user
     * @param user User that is going to be modified and its new values
     */
    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML})
    public void modifyUser(@PathParam("id") Integer userId, User user) {
        user.setPassword(CipheringManager.hashCipher(new String(CipheringManager.decipherText(user.getPassword().getBytes()))));
        ejbUser.modifyUser(user);
    }

    /**
     * Method that modifies the password of the specified user
     *
     * @param password New password for the user
     * @param user User to change
     */
    @PUT
    @Path("password/{password}")
    @Consumes({MediaType.APPLICATION_XML})
    public void modifyPassword(@PathParam("password") String password, User user) {
        user.setPassword(CipheringManager.hashCipher(new String(CipheringManager.decipherText(password.getBytes()))));
        user.setLastPasswordChange(Date.valueOf(LocalDate.now()));

        ejbUser.modifyUser(user);
    }

    /**
     * Method that deletes a user from the database
     *
     * @param userId Id of the user that is going to be deleted
     */
    @DELETE
    @Path("{id}")
    public void deleteUser(@PathParam("id") Integer userId) {
        ejbUser.deleteUser(userId);
    }

    /**
     * Method that searches for a user
     *
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
     * Method that searches for a user with the specified email
     *
     * @param email EmailSender of the user to find
     * @return The user found
     */
    @GET
    @Path("email/{email}")
    @Produces({MediaType.APPLICATION_XML})
    public User findUserByEmail(@PathParam("email") String email) {
        return ejbUser.findUserByEmail(email);
    }

    /**
     * Method to check the credentials of a user
     *
     * @param login Login of the user
     * @param password Password of the user
     * @return The user, if found
     */
    @GET
    @Path("credentials/{login}/{password}")
    @Produces({MediaType.APPLICATION_XML})
    public User checkUserPassword(@PathParam("login") String login, @PathParam("password") String password) {
        User user = ejbUser.checkUserPassword(login, CipheringManager.hashCipher(new String(CipheringManager.decipherText(password.getBytes()))));

        if (user != null) {
            user.setLastLogin(Date.valueOf(LocalDate.now()));
            ejbUser.modifyUser(user);
        }

        return user;
    }

    /**
     * Finds and returns a list containing all the users from the database
     *
     * @return List of type User with all the users found
     */
    @GET
    @Produces({MediaType.APPLICATION_XML})
    public Set<User> findAllUsers() {
        return ejbUser.findAllUsers();
    }

    /**
     * This method receives an email and checks if there is a user with that
     * email in the database. If the user exists, generates a temporary 8
     * character long alphanumeric code and returns it
     * 
     * @param email Email of the user
     * @return String Recovery code for the client
     */
    @GET
    @Path("email/{email}")
    @Produces({MediaType.TEXT_PLAIN})
    public String getRecoveryCode(@PathParam("email") String email) {

        String recoveryCode = null;
        
        if (this.findUserByEmail(email) != null) {
            SecureRandom random = new SecureRandom();
            String validChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789abcdefghijklmnopqrstuvwxyz";

            for (int i = 0; i < 8; i++) {
                recoveryCode += validChars.charAt(1 + random.nextInt(validChars.length()));
            }

            EmailSender emailSender = new EmailSender();
            emailSender.sendRecoveryMail(email, recoveryCode);
        }

        return recoveryCode;
    }
}
