package equipo2_crudapp_server.service;

import equipo2_crudapp_server.entities.Offer;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
     * This function creates a new entry in the database with the given offer.
     *
     * @param offer New offer to be added.
     */
    @Override
    public void createOffer(Offer offer) {
        entityManager.persist(offer);
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
     * @param offerId ID of the offer to be deleted.
     */
    @Override
    public void deleteOffer(Integer offerId) {
        entityManager.remove(entityManager.merge(offerId));
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
    public List<Offer> findAllOffers() {
        return entityManager.createNamedQuery("findAllOffers").getResultList();
    }
}
