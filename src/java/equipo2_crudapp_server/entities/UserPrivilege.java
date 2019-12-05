/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package equipo2_crudapp_server.entities;

/**
 * Enumerator for the user privilege on the application. It can be USER or ADMIN
 * @author Diego Corral
 */
public enum UserPrivilege {
    
    /**
     * Value for the normal users of the application
     */
    USER,
    
    /**
     * Value for the admins of the application
     */
    ADMIN;
}
