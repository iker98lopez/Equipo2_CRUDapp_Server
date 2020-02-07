/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package equipo2_crudapp_server.service;

import equipo2_crudapp_classes.enumerators.UserPrivilege;
import equipo2_crudapp_classes.enumerators.UserStatus;
import equipo2_crudapp_server.ciphering.CipheringManager;
import equipo2_crudapp_server.email.EmailSender;
import equipo2_crudapp_server.entities.User;
import java.security.SecureRandom;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Set;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.ServerErrorException;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.DatatypeConverter;

/**
 * RESTful service provider for the entity User.
 *
 * @author Diego Corral
 */
@Path("user")
public class UserREST {

    private static final Logger LOGGER = Logger.getLogger("equipo2_crudapp_server.service.UserREST");

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
        byte[] password = DatatypeConverter.parseHexBinary(user.getPassword());
        byte[] decipheredPassword = CipheringManager.decipherText(password);
        String hashedPassword = DatatypeConverter.printHexBinary(CipheringManager.hashCipher(decipheredPassword));

        user.setPassword(hashedPassword);
        user.setLastLogin(Date.valueOf(LocalDate.now()));
        user.setLastPasswordChange(Date.valueOf(LocalDate.now()));
        user.setPrivilege(UserPrivilege.USER);
        user.setStatus(UserStatus.ENABLED);

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
        user.setPassword(ejbUser.findUser(userId).getPassword());
        ejbUser.modifyUser(user);
    }

    /**
     * Method that modifies the password of the specified user
     *
     * @param newPassword new password for the user
     * @param user User to change their password
     */
    @PUT
    @Path("password/{password}")
    @Consumes({MediaType.APPLICATION_XML})
    public void modifyPassword(@PathParam("password") String newPassword, User user) {
        user = ejbUser.findUserByLogin(user.getLogin());
        
        byte[] decipheredPassword = CipheringManager.decipherText(DatatypeConverter.parseHexBinary(newPassword));
        String hashedPassword = DatatypeConverter.printHexBinary(CipheringManager.hashCipher(decipheredPassword));
        user.setPassword(hashedPassword);
        user.setLastPasswordChange(Date.valueOf(LocalDate.now()));
        
        ejbUser.modifyUser(user);
        
        EmailSender emailSender = new EmailSender();
        emailSender.sendPasswordChangeMail(user.getEmail());
    }

    /**
     * Method that deletes a user from the database
     *
     * @param user user to delete
     */
    @DELETE
    @Path("{user}")
    public void deleteUser(@PathParam("user") User user) {
        ejbUser.deleteUser(user);
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
        User user = ejbUser.findUser(userId);
        user.setPassword(null);
        return user;
    }

    /**
     * Method that searches for a user
     *
     * @param login Login of the user to find
     * @return The user found
     */
    @GET
    @Path("login/{login}")
    @Produces({MediaType.APPLICATION_XML})
    public User findUserByLogin(@PathParam("login") String login) {
        User user = ejbUser.findUserByLogin(login);
        user.setPassword(null);
        return user;
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
        User user = ejbUser.findUserByEmail(email);
        user.setPassword(null);
        return user;
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
        byte[] decipheredPassword = CipheringManager.decipherText(DatatypeConverter.parseHexBinary(password));
        String hashedPassword = DatatypeConverter.printHexBinary(CipheringManager.hashCipher(decipheredPassword));
        User user = ejbUser.checkUserPassword(login, hashedPassword);

        if (user != null) {
            user.setLastLogin(Date.valueOf(LocalDate.now()));
            ejbUser.modifyUser(user);
            user.setPassword(null);
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

        String recoveryCode = "";

        if (this.findUserByEmail(email) != null) {
            SecureRandom random = new SecureRandom();
            String validChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789abcdefghijklmnopqrstuvwxyz";

            for (int i = 0; i < 6; i++) {
                recoveryCode += validChars.charAt(random.nextInt(validChars.length()));
            }

            EmailSender emailSender = new EmailSender();
            emailSender.sendRecoveryMail(email, recoveryCode);
        }
        
        
        String hashedPassword = DatatypeConverter.printHexBinary(CipheringManager.hashCipher(recoveryCode.getBytes()));

        User user = ejbUser.findUserByEmail(email);
        user.setPassword(hashedPassword);
        user.setLastPasswordChange(Date.valueOf(LocalDate.now()));
        
        ejbUser.modifyUser(user);
        
        return recoveryCode;
    }
}
