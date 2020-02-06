/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package equipo2_crudapp_server.service;

import equipo2_crudapp_server.entities.Comment;
import java.util.HashSet;
import java.util.Set;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Enterprise Java Bean that contains all the logic for the entity Comment.
 * 
 * @author Adrián García
 */
@Stateless
public class EJBComment implements EJBCommentInterface {
    
    @PersistenceContext(unitName = "Equipo2_CRUDapp_ServerPU")
    private EntityManager entityManager;

    /**
     * This function creates a new entry in the database with the given comment.
     * 
     * @param comment New comment to be added.
     */
    @Override
    public void createComment(Comment comment) {
        entityManager.persist(comment);
    }

    /**
     * This function updates an existing comment with the data from the given 
     * comment.
     * 
     * @param comment Comment with the modifications.
     */
    @Override
    public void modifyComment(Comment comment) {
        entityManager.merge(comment);
        entityManager.flush();
    }

    /**
     * This function deletes an existing comment. It is found by the given ID.
     * 
     * @param comment Comment to be deleted.
     */
    @Override
    public void deleteComment(Comment comment) {
        entityManager.remove(entityManager.merge(comment));
    }

    /**
     * This function finds a comment by the id it receives and returns it.
     * 
     * @param id ID of the comment.
     * @return Comment, if found.
     */
    @Override
    public Comment findComment(Integer id) {
        return entityManager.find(Comment.class, id);
    }
    
    /**
     * This function finds all comments in the database and returns them.
     *
     * @return List of type Comment with all the comments in the database.
     */
    @Override
    public Set<Comment> findAllComments() {
        return new HashSet<>(entityManager.createNamedQuery("findAllComments").getResultList());
    }
}
