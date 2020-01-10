/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package equipo2_crudapp_server.service;

import equipo2_crudapp_server.entities.Comment;
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
 * RESTful service provider for the entity Comment.
 * 
 * @author Adrián García
 */
@Path("Comment")
public class CommentREST {
    
    /**
     * Enterprise Java Beans for the entity comment
     */
    @EJB
    private EJBCommentInterface ejbComment;

    /**
     * Method that inserts a comment in the database
     * @param comment The comment that is going to be inserted
     */
    @POST
    @Consumes({MediaType.APPLICATION_XML})
    public void createComment(Comment comment) {
        ejbComment.createComment(comment);
    }

    /**
     * Method that modifies a comment in the database
     * @param commentId Id of the comment
     * @param comment the comment that is going to be modified and its new values
     */
    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML})
    public void modifyComment(@PathParam("id") Integer commentId, Comment comment) {
        ejbComment.modifyComment(comment);
    }
    
    /**
     * Method that deletes a comment from the database
     * @param commentId Id of the comment to delete
     */
    @DELETE
    @Path("{id}")
    public void removeComment(@PathParam("id") Integer commentId) {
        ejbComment.deleteComment(commentId);
    }

    /**
     * Method that finds a comment
     * @param commentId Id of the comment to search
     * @return The found comment
     */
    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML})
    public Comment findComment(@PathParam("id") Integer commentId) {
        return ejbComment.findComment(commentId);
    }
    
    /**
     * Finds and returns a list containing all the comments from the database.
     *
     * @return List of type Comment with all the comments found.
     */
    @GET
    @Produces({MediaType.APPLICATION_XML})
    public List<Comment> findAllComments() {
        return ejbComment.findAllComments();
    }
}
