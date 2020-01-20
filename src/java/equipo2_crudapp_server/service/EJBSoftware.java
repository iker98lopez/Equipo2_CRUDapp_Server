package equipo2_crudapp_server.service;

import equipo2_crudapp_server.entities.Software;
import java.util.Set;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Enterprise Java Bean that contains all the logic for the entity Software.
 *
 * @author iker lopez carrillo
 */
@Stateless
public class EJBSoftware implements EJBSoftwareInterface {

    @PersistenceContext(unitName = "Equipo2_CRUDapp_ServerPU")
    private EntityManager entityManager;

    /**
     * This function creates a new entry in the database with the given
     * software.
     *
     * @param software New software to be added.
     */
    @Override
    public void createSoftware(Software software) {
        entityManager.persist(software);
    }

    /**
     * This function updates an existing software with the data from the given
     * software.
     *
     * @param software Software with the modifications.
     */
    @Override
    public void modifySoftware(Software software) {
        entityManager.merge(software);
        entityManager.flush();
    }

    /**
     * This function deletes an existing software. It is found by the given ID.
     *
     * @param softwareId ID of the software to be deleted.
     */
    @Override
    public void deleteSoftware(Integer softwareId) {
        entityManager.remove(entityManager.merge(softwareId));
    }

    /**
     * This function finds a software by the id it receives and returns it.
     *
     * @param id ID of the software.
     * @return Software, if found.
     */
    @Override
    public Software findSoftware(Integer id) {
        return entityManager.find(Software.class, id);
    }

    /**
     * This function finds all softwares in the database and returns them.
     *
     * @return List of type Software with all the softwares in the database.
     */
    @Override
    public Set<Software> findAllSoftwares() {
        return (Set) entityManager.createNamedQuery("findAllSoftwares").getResultList();
    }

    /**
     * This function finds all softwares with a name that contains the String
     * received.
     *
     * @return List of type Software with all the softwares from the result of
     * the query.
     */
    @Override
    public Set<Software> findSoftwaresByName(String name) {
        return (Set) entityManager.createNamedQuery("findSoftwaresByName").setParameter("name", name).getResultList();
    }
}
