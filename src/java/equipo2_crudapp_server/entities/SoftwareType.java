/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package equipo2_crudapp_server.entities;

/**
 * Enumerator for Software to determine its type. It can be PROGRAM, GAME or
 * EXTENSION.
 *
 * @author iker lopez carrillo
 */
public enum SoftwareType {
    
    /**
     * Value for softwares that are computer programs.
     */
    PROGRAM, 
    
    /**
     * Value for softwares that are games.
     */
    GAME, 
    
    /**
     * Value for softwares that are an extension of another software.
     */
    EXTENSION;
}