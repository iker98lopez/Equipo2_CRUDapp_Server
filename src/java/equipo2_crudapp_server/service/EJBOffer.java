package equipo2_crudapp_server.service;

import equipo2_crudapp_server.entities.Offer;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;
import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.ConstraintViolationException;

/**
 * Enterprise Java Bean that contains all the logic for the entity Offer.
 *
 * @author iker lopez carrillo
 */
@Stateless
public class EJBOffer implements EJBOfferInterface {

    @PersistenceContext(unitName = "Equipo2_CRUDapp_ServerPU")
    private EntityManager entityManager;
    
    /**
     * Logger to show error messages and exceptions.
     */
    private static final Logger LOGGER = Logger.getLogger("equipo2_crudapp_server.services.EJBOffer");

    /**
     * This function creates a new entry in the database with the given offer.
     *
     * @param offer New offer to be added.
     */
    @Override
    public void createOffer(Offer offer) {
        try {
            entityManager.persist(offer);
        } catch (ConstraintViolationException exception) {
            LOGGER.warning("Validation failed for the new offer. " + exception.getMessage());
            throw new EJBException(exception.getMessage());
        }
    }

    /**
     * This function updates an existing offer with the data from the given
     * offer.
     *
     * @param offer Offer with the modifications.
     */
    @Override
    public void modifyOffer(Offer offer) {
        entityManager.merge(offer);
        entityManager.flush();
    }

    /**
     * This function deletes an existing offer. It is found by the given ID.
     *
     * @param offer Offer to be deleted.
     */
    @Override
    public void deleteOffer(Offer offer) {
        entityManager.remove(entityManager.merge(offer));
    }

    /**
     * This function finds an offer by the ID it receives and returns it.
     *
     * @param id ID of the offer.
     * @return Offer, if found.
     */
    @Override
    public Offer findOffer(Integer id) {
        return entityManager.find(Offer.class, id);
    }

    /**
     * This function finds all offers in the database and returns them.
     *
     * @return List of type Offer with all the offers in the database.
     */
    @Override
    public Set<Offer> findAllOffers() {
        return new HashSet<>(entityManager.createNamedQuery("findAllOffers").getResultList());
    }
}
