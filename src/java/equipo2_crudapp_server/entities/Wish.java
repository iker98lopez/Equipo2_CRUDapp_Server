/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package equipo2_crudapp_server.entities;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Entity;
import static javax.persistence.FetchType.EAGER;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * Entity for Wish
 *
 * @author Adrián García
 */
@Entity
@Table(name = "wish", schema = "equipo2crudappdb")
@NamedQuery(name = "findAllWishes",
        query = "SELECT a FROM Wish a ORDER BY a.id DESC")
@XmlRootElement
public class Wish implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * The wishId of wish
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer wishId;

    /**
     * The software in the wish
     */
    //@MapsId("softwareId")
    @ManyToOne(fetch = EAGER)
    private Software software;

    /**
     * User owner of the wish
     */
    //@MapsId("userId")
    @ManyToOne(fetch = EAGER)
    private User user;

    /**
     * @return the wishId
     */
    public Integer getWishId() {
        return wishId;
    }

    /**
     * @param wishId the wishId to set
     */
    public void setWishId(Integer wishId) {
        this.wishId = wishId;
    }

    /**
     * @return the software
     */
    public Software getSoftware() {
        return software;
    }

    /**
     * @param software the software to set
     */
    public void setSoftware(Software software) {
        this.software = software;
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
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.wishId);
        hash = 67 * hash + Objects.hashCode(this.software);
        hash = 67 * hash + Objects.hashCode(this.user);
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
        final Wish other = (Wish) obj;
        if (!Objects.equals(this.wishId, other.wishId)) {
            return false;
        }
        if (!Objects.equals(this.software, other.software)) {
            return false;
        }
        if (!Objects.equals(this.user, other.user)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Wish{" + "wishId=" + wishId + ", software=" + software + ", user=" + user + '}';
    }
}
