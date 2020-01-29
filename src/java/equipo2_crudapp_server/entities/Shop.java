/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package equipo2_crudapp_server.entities;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;
import javax.persistence.Basic;
import static javax.persistence.CascadeType.ALL;
import javax.persistence.Column;
import javax.persistence.Entity;
import static javax.persistence.FetchType.EAGER;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * Entity for shop.
 *
 * @author iker lopez carrillo
 */
@Entity
@Table(name = "shop", schema = "equipo2crudappdb")
@NamedQuery(name = "findAllShops",
            query = "SELECT a FROM Shop a ORDER BY a.id DESC")
@XmlRootElement
public class Shop implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID of the shop.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer shopId;

    /**
     * Name of the shop.
     */
    @NotNull
    @Column(unique = true)
    private String name;

    /**
     * URL of the shop.
     */
    @NotNull
    private String url;

    /**
     * Image or logo of the shop.
     */
    @Lob
    @Basic(fetch=EAGER)
    private byte[] image;

    @OneToMany (mappedBy = "offerId", fetch = EAGER, cascade = ALL)
    private Set<Offer> offers;
    
    /**
     * @return the shopId
     */
    public Integer getShopId() {
        return shopId;
    }

    /**
     * @param shopId the shopId to set
     */
    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param url the url to set
     */
    public void setUrl(String url) {
        this.url = url;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    @XmlTransient
    public Set<Offer> getOffers() {
        return offers;
    }

    public void setOffers(Set<Offer> offers) {
        this.offers = offers;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 13 * hash + Objects.hashCode(this.shopId);
        hash = 13 * hash + Objects.hashCode(this.name);
        hash = 13 * hash + Objects.hashCode(this.url);
        hash = 13 * hash + Objects.hashCode(this.image);
        hash = 13 * hash + Objects.hashCode(this.offers);
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
        final Shop other = (Shop) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.url, other.url)) {
            return false;
        }
        if (!Objects.equals(this.shopId, other.shopId)) {
            return false;
        }
        if (!Objects.equals(this.image, other.image)) {
            return false;
        }
        if (!Objects.equals(this.offers, other.offers)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Shop{" + "shopId=" + shopId + ", name=" + name + ", url=" + url + ", image=" + image + ", offers=" + offers + '}';
    }
}
