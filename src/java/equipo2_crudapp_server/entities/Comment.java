/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package equipo2_crudapp_server.entities;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * Comment entity
 *
 * @author Diego Corral
 */
@Entity
@Table(name = "comment", schema = "equipo2crudappdb")
@NamedQuery(name = "findAllComments",
            query = "SELECT a FROM Comment a ORDER BY a.id DESC")
@XmlRootElement
public class Comment implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * The comment id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer commentId;

    /**
     * The text of the comment
     */
    @NotNull
    private String comment;

    /**
     * The user that has created the comment
     */
    @NotNull
    @ManyToOne
    private User user;

    /**
     * @return the commentId
     */
    public Integer getCommentId() {
        return commentId;
    }

    /**
     * @param commentId the commentId to set
     */
    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    /**
     * @return the comment
     */
    public String getComment() {
        return comment;
    }

    /**
     * @param comment the comment to set
     */
    public void setComment(String comment) {
        this.comment = comment;
    }

    /**
     * @return the user
     */
    @XmlTransient
    public User getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 43 * hash + Objects.hashCode(this.commentId);
        hash = 43 * hash + Objects.hashCode(this.comment);
        hash = 43 * hash + Objects.hashCode(this.user);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Comment other = (Comment) obj;
        if (!Objects.equals(this.comment, other.comment)) {
            return false;
        }
        if (!Objects.equals(this.commentId, other.commentId)) {
            return false;
        }
        if (!Objects.equals(this.user, other.user)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Comment{" + "commentId=" + commentId + ", comment=" + comment + ", user=" + user + '}';
    }
}
