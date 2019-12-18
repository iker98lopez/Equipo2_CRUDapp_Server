/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package equipo2_crudapp_server.service;

import equipo2_crudapp_server.entities.Comment;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Adrián García
 */
@Local
public interface EJBCommentInterface {

    /**
     * This function creates a new entry in the database with the given comment.
     * 
     * @param comment New comment to be added.
     */
    public void createComment(Comment comment);

    /**
     * This function updates an existing comment with the data from the given comment.
     * 
     * @param comment Wish with the modifications.
     */
    public void modifyComment(Comment comment);

    /**
     * This function deletes an existing comment. It is found by the given ID.
     * 
     * @param comment ID of the comment to be deleted.
     */
    public void deleteComment(Integer commentID);

    /**
     * This function finds a comment by the id it receives and returns it.
     * 
     * @param id ID of the comment
     * @return Comment, if found.
     */
    public Comment findComment(Integer id);

    /**
     * This function finds all comments in the database and returns them.
     *
     * @return List of type Comment with all the comments in the database.
     */
    public List<Comment> findAllComments();
}
