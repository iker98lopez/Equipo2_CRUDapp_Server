package equipo2_crudapp_server.service;

import equipo2_crudapp_server.entities.Software;
import java.util.List;
import javax.ejb.Local;

/**
 * Interface implemented by EJBSoftware.
 * 
 * @author iker lopez carrillo
 */
@Local
public interface EJBSoftwareInterface {

    /**
     * This function creates a new entry in the database with the given
     * software.
     *
     * @param software New software to be added.
     */
    public void createSoftware(Software software);

    /**
     * This function updates an existing software with the data from the given
     * software.
     *
     * @param software Software with the modifications.
     */
    public void modifySoftware(Software software);

    /**
     * This function deletes an existing software. It is found by the given ID.
     *
     * @param softwareId ID of the software to be deleted.
     */
    public void deleteSoftware(Integer softwareId);

    /**
     * This function finds a software by the id it receives and returns it.
     *
     * @param id ID of the software.
     * @return Software, if found.
     */
    public Software findSoftware(Integer id);

    /**
     * This function finds all softwares in the database and returns them.
     *
     * @return List of type Software with all the softwares in the database.
     */
    public List<Software> findAllSoftwares();

    /**
     * This function finds all softwares with a name that contains the String
     * received.
     *
     * @param name Name to be found in the softwares.
     * @return List of type Software with all the softwares from the result of
     * the query.
     */
    public List<Software> findSoftwaresByName(String name);
}
