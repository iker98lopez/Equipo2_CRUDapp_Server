/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package equipo2_crudapp_server.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import java.util.Set;
import static javax.persistence.CascadeType.ALL;
import javax.persistence.Entity;
import static javax.persistence.FetchType.EAGER;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * Entity for Offer.
 *
 * @author iker lopez carrillo
 */
@Entity
@Table(name = "offer", schema = "equipo2crudappdb")
@NamedQuery(name = "findAllOffers",
        query = "SELECT a FROM Offer a ORDER BY a.offerId DESC")
@XmlRootElement
public class Offer implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID of the offer.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer offerId;

    /**
     * URL of the offer.
     */
    @NotNull
    private String url;

    /**
     * Date of expiration for the offer.
     */
    @NotNull
    @Temporal(TemporalType.DATE)
    private Date expiringDate;

    /**
     * Base price of the offer.
     */
    private Double basePrice;

    /**
     * Discounted price of the offer.
     */
    private Double dicountedPrice;

    /**
     * Percentage of discount of the offer.
     */
    private Integer discount;

    /**
     * User author of the offer.
     */
    @NotNull
    @ManyToOne
    private User user;

    /**
     * Software offered in the offer.
     */
    @ManyToOne(fetch = EAGER)
    private Software software;

    /**
     * Shop providing the offer.
     */
    @ManyToOne(fetch = EAGER)
    private Shop shop;

    /**
     * Set of comments of the offer.
     */
    @OneToMany(mappedBy = "commentId", fetch = EAGER, cascade = ALL)
    private Set<Comment> comments;

    /**
     * @return the offerId
     */
    public Integer getOfferId() {
        return offerId;
    }

    /**
     * @param offerId the offerId to set
     */
    public void setOfferId(Integer offerId) {
        this.offerId = offerId;
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

    /**
     * @return the expiringDate
     */
    public Date getExpiringDate() {
        return expiringDate;
    }

    /**
     * @param expiringDate the expiringDate to set
     */
    public void setExpiringDate(Date expiringDate) {
        this.expiringDate = expiringDate;
    }

    /**
     * @return the basePrice
     */
    public Double getBasePrice() {
        return basePrice;
    }

    /**
     * @param basePrice the basePrice to set
     */
    public void setBasePrice(Double basePrice) {
        this.basePrice = basePrice;
    }

    /**
     * @return the dicountedPrice
     */
    public Double getDicountedPrice() {
        return dicountedPrice;
    }

    /**
     * @param dicountedPrice the dicountedPrice to set
     */
    public void setDicountedPrice(Double dicountedPrice) {
        this.dicountedPrice = dicountedPrice;
    }

    /**
     * @return the discount
     */
    public Integer getDiscount() {
        return discount;
    }

    /**
     * @param discount the discount to set
     */
    public void setDiscount(Integer discount) {
        this.discount = discount;
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

    /**
     * @return the software
     */
    @XmlTransient
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
     * @return the shop
     */
    public Shop getShop() {
        return shop;
    }

    /**
     * @param shop the shop to set
     */
    public void setShop(Shop shop) {
        this.shop = shop;
    }

    /**
     * @return the comments
     */
    public Set<Comment> getComments() {
        return comments;
    }

    /**
     * @param comments the comments to set
     */
    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + Objects.hashCode(this.offerId);
        hash = 53 * hash + Objects.hashCode(this.url);
        hash = 53 * hash + Objects.hashCode(this.expiringDate);
        hash = 53 * hash + Objects.hashCode(this.basePrice);
        hash = 53 * hash + Objects.hashCode(this.dicountedPrice);
        hash = 53 * hash + Objects.hashCode(this.discount);
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
        final Offer other = (Offer) obj;
        if (!Objects.equals(this.url, other.url)) {
            return false;
        }
        if (!Objects.equals(this.offerId, other.offerId)) {
            return false;
        }
        if (!Objects.equals(this.expiringDate, other.expiringDate)) {
            return false;
        }
        if (!Objects.equals(this.basePrice, other.basePrice)) {
            return false;
        }
        if (!Objects.equals(this.dicountedPrice, other.dicountedPrice)) {
            return false;
        }
        if (!Objects.equals(this.discount, other.discount)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Offer{" + "offerId=" + offerId + ", url=" + url + ", expiringDate=" + expiringDate + ", basePrice=" + basePrice + ", dicountedPrice=" + dicountedPrice + ", discount=" + discount + '}';
    }
}
