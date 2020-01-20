package equipo2_crudapp_server.service;

import equipo2_crudapp_server.entities.Software;
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
 * RESTful service provider for the entity Software.
 *
 * @author iker lopez carrillo
 */
@Path("software")
public class SoftwareREST {

    /**
     * Enterprise Java Beans for the entity Software.
     */
    @EJB
    private EJBSoftwareInterface ejbSoftware;

    /**
     * Creates a new software in the database.
     *
     * @param software Software to be added.
     */
    @POST
    @Consumes({MediaType.APPLICATION_XML})
    public void createSoftware(Software software) {
        ejbSoftware.createSoftware(software);
    }

    /**
     * Modifies an existing software with the data from the one received.
     *
     * @param id ID of the software to modify.
     * @param software Software with the changes to apply.
     */
    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML})
    public void editSoftware(@PathParam("id") Integer id, Software software) {
        ejbSoftware.modifySoftware(software);
    }

    /**
     * Deletes a software from the database.
     *
     * @param id ID of the software to delete.
     */
    @DELETE
    @Path("{id}")
    public void removeSoftware(@PathParam("id") Integer id) {
        ejbSoftware.deleteSoftware(id);
    }

    /**
     * Finds a software by its ID.
     *
     * @param id ID of the software to find.
     * @return Software, if found.
     */
    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML})
    public Software findSoftware(@PathParam("id") Integer id) {
        return ejbSoftware.findSoftware(id);
    }

    /**
     * Finds and returns a list containing all the softwares from the database.
     *
     * @return List with all the softwares found.
     */
    @GET
    @Produces({MediaType.APPLICATION_XML})
    public Set<Software> findAllSoftwares() {
        return ejbSoftware.findAllSoftwares();
    }

    /**
     * Finds a software by name coincidence.
     *
     * @param name Name to be searched.
     * @return Software, if found.
     */
    @GET
    @Path("findByName/{name}")
    @Produces({MediaType.APPLICATION_XML})
    public Set<Software> findSoftwaresByName(@PathParam("name") String name) {
        return ejbSoftware.findSoftwaresByName(name);
    }
}
