/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package equipo2_crudapp_server.service;

import java.util.Set;
import javax.ws.rs.core.Application;

/**
 *
 * @author Iker lopez carrillo
 */
@javax.ws.rs.ApplicationPath("webresources")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method. It is automatically
     * populated with all resources defined in the project. If required, comment
     * out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(equipo2_crudapp_server.service.CommentREST.class);
        resources.add(equipo2_crudapp_server.service.OfferREST.class);
        resources.add(equipo2_crudapp_server.service.ShopREST.class);
        resources.add(equipo2_crudapp_server.service.SoftwareREST.class);
        resources.add(equipo2_crudapp_server.service.UserREST.class);
        resources.add(equipo2_crudapp_server.service.WishREST.class);
    }
}
