package equipo2_crudapp_server.service;

import equipo2_crudapp_server.entities.Offer;
import equipo2_crudapp_server.entities.Software;
import equipo2_crudapp_server.entities.User;
import java.util.Set;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
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

/**
 * RESTful service provider for the entity Offer.
 *
 * @author iker lopez carrillo
 */
@Path("offer")
public class OfferREST {

    /**
     * Logger to show error messages and exceptions.
     */
    private static final Logger LOGGER = Logger.getLogger("equipo2_crudapp_server.services.OfferREST");

    /**
     * Enterprise Java Beans for the entity Offer.
     */
    @EJB
    private EJBOfferInterface ejbOffer;

    /**
     * Enterprise Java Beans for the entity Offer.
     */
    @EJB
    private EJBSoftwareInterface ejbSoftware;

    /**
     * Enterprise Java Beans for the entity User.
     */
    @EJB
    private EJBUserInterface ejbUser;
    
    /**
     * Creates a new offer in the database.
     *
     * @param offer Offer to be added.
     */
    @POST
    @Consumes({MediaType.APPLICATION_XML})
    public void createOffer(Offer offer) {
        try {
            Software software = ejbSoftware.findAllSoftwares().stream().filter(sw -> sw.getOffers() != null
                && sw.getOffers().contains(offer))
                .findFirst().get();
            offer.setSoftware(software);
            User user = ejbUser.findAllUsers().stream().filter(usr -> usr.getOffers() != null 
                    && usr.getOffers().contains(offer))
                    .findFirst().get();
            offer.setUser(user);
            ejbOffer.createOffer(offer);
        } catch (EJBException exception) {
            LOGGER.warning("There was an error creating a new offer. " + exception.getMessage());
        } catch (Exception exception) {
            LOGGER.warning("There was an unexpected exception. " + exception.getClass() + " " + exception.getMessage());
        }
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
    public void editOffer(@PathParam("id") Integer id, Offer offer) {
        Software software = ejbSoftware.findAllSoftwares().stream().filter(sw -> sw.getOffers() != null
                && sw.getOffers().contains(offer))
                .findFirst().get();
        offer.setSoftware(software);
        User user = ejbUser.findAllUsers().stream().filter(usr -> usr.getOffers() != null 
                && usr.getOffers().contains(offer))
                .findFirst().get();
        offer.setUser(user);
        ejbOffer.modifyOffer(offer);
    }

    /**
     * Deletes an offer from the database.
     *
     * @param offerId ID of the offer to delete.
     */
    @DELETE
    @Path("{id}")
    public void removeOffer(@PathParam("id") int offerId) {
        ejbOffer.deleteOffer(ejbOffer.findOffer(offerId));
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
    public Offer findOffer(@PathParam("id") Integer id) {
        return ejbOffer.findOffer(id);
    }

    /**
     * Finds and returns a list containing all the offers from the database.
     *
     * @return List of type Comment with all the offers found.
     */
    @GET
    @Produces({MediaType.APPLICATION_XML})
    public Set<Offer> findAllOffers() {
        return ejbOffer.findAllOffers();
    }
}
