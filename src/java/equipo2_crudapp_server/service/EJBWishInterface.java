/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package equipo2_crudapp_server.service;

import equipo2_crudapp_server.entities.Wish;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Adrián García
 */
@Local
public interface EJBWishInterface {

    /**
     * This function creates a new entry in the database with the given wish.
     * 
     * @param wish New wish to be added.
     */
    public void createWish(Wish wish);

    /**
     * This function updates an existing wish with the data from the given 
     * wish.
     * 
     * @param wish Wish with the modifications.
     */
    public void modifyWish(Wish wish);

    /**
     * This function deletes an existing wish. It is found by the given ID.
     * 
     * @param wish ID of the software to be deleted.
     */
    public void deleteWish(Integer wishID);

    /**
     * This function finds a wish by the id it receives and returns it.
     * 
     * @param id ID of the wish.
     * @return Wish, if found.
     */
    public Wish findWish(Integer id);

    /**
     * Finds and returns a list containing all the wishes from the database.
     *
     * @return List of type Wish with all the wishes found.
     */
    public List<Wish> findAllWishes();
}
