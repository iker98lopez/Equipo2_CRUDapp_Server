/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package equipo2_crudapp_server.entities;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Adrián García
 */

@Entity
@Table(name = "wish", schema = "equipo2crudappdb")
@XmlRootElement
public class Wish implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * The id of wish
     */
    @Id
    private Integer id;
    /**
     * The software in the wish
     */
    private Software software;
    /**
     * Minimum price to be notified when software drops its price
     */
    private Double minPrice;

    /**
     *
     * @return the wish id
     */
    public Integer getId() {
        return id;
    }

    /**
     *
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
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
     * @return the minPrice
     */
    public Double getMinPrice() {
        return minPrice;
    }

    /**
     * @param minPrice the minPrice to set
     */
    public void setMinPrice(Double minPrice) {
        this.minPrice = minPrice;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + Objects.hashCode(this.id);
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
        return true;
    }

    @Override
    public String toString() {
        return "Wish{" + "id=" + id + ", software=" + software + ", minPrice=" + minPrice + '}';
    }
}
