package equipo2_crudapp_server.service;

import equipo2_crudapp_server.entities.Offer;
import java.util.List;
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
 * RESTful service provider for the entity Offer.
 *
 * @author iker lopez carrillo
 */
@Path("offer")
public class OfferREST {

    /**
     * Enterprise Java Beans for the entity Offer.
     */
    @EJB
    private EJBOfferInterface ejbOffer;

    /**
     * Creates a new offer in the database.
     *
     * @param offer Offer to be added.
     */
    @POST
    @Consumes({MediaType.APPLICATION_XML})
    public void createOffer(Offer offer) {
        ejbOffer.createOffer(offer);
    }

    /**
     * Modifies an existing offer with the data from the one received.
     *
     * @param id ID of the offer to modify.
     * @param offer Offer with the changes to apply.
     */
    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML})
    public void edit(@PathParam("id") Integer id, Offer offer) {
        ejbOffer.modifyOffer(offer);
    }

    /**
     * Deletes an offer from the database.
     *
     * @param id ID of the offer to delete.
     */
    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer id) {
        ejbOffer.deleteOffer(id);
    }

    /**
     * Finds an offer by its ID.
     *
     * @param id ID of the offer to find.
     * @return Offer, if found.
     */
    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML})
    public Offer find(@PathParam("id") Integer id) {
        return ejbOffer.findOffer(id);
    }

    /**
     * Finds and returns a list containing all the offers from the database.
     *
     * @return List with all the offers found.
     */
    @GET
    @Produces({MediaType.APPLICATION_XML})
    public List<Offer> findAll() {
        return ejbOffer.findAllOffers();
    }
}
