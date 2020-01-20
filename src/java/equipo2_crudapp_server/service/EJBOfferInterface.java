package equipo2_crudapp_server.service;

import equipo2_crudapp_server.entities.Offer;
import java.util.Set;
import javax.ejb.Local;

/**
 * Interface implemented by EJBOffer.
 * 
 * @author iker lopez carrillo
 */
@Local
public interface EJBOfferInterface {

    /**
     * This function creates a new entry in the database with the given offer.
     *
     * @param offer New offer to be added.
     */
    public void createOffer(Offer offer);

    /**
     * This function updates an existing offer with the data from the given
     * offer.
     *
     * @param offer Offer with the modifications.
     */
    public void modifyOffer(Offer offer);

    /**
     * This function deletes an existing offer. It is found by the given ID.
     *
     * @param offerId ID of the offer to be deleted.
     */
    public void deleteOffer(Integer offerId);

    /**
     * This function finds a offer by the id it receives and returns it.
     *
     * @param id ID of the offer.
     * @return Offer, if found.
     */
    public Offer findOffer(Integer id);

    /**
     * This function finds all offers in the database and returns them.
     *
     * @return List of type Offer with all the offers in the database.
     */
    public Set<Offer> findAllOffers();
}
