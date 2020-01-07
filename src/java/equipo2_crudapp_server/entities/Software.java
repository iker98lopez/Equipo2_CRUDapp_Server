package equipo2_crudapp_server.entities;

import java.io.Serializable;
import java.sql.Blob;
import java.util.Date;
import java.util.Objects;
import java.util.Set;
import javax.persistence.Basic;
import static javax.persistence.CascadeType.ALL;
import javax.persistence.Entity;
import static javax.persistence.FetchType.EAGER;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * Entity for Software.
 *
 * @author iker lopez carrillo
 */
@Entity
@Table(name = "software", schema = "equipo2crudappdb")
@NamedQueries({
    @NamedQuery(name = "findAllSoftwares",
            query = "SELECT a FROM Software a ORDER BY a.id DESC")
    ,
    @NamedQuery(name = "findSoftwaresByName",
            query = "SELECT a FROM Software a WHERE a.name LIKE CONCAT('%', :name, '%')")
})
@XmlRootElement
public class Software implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID for the software.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer softwareId;

    /**
     * Name of the software.
     */
    @NotNull
    private String name;

    /**
     * Publisher of the software.
     */
    private String publisher;

    /**
     * Description of the software.
     */
    private String description;

    /**
     * Image of the software.
     */
    @Lob
    @Basic(fetch=EAGER)
    private byte[] image;

    /**
     * Release date of the software
     */
    @Temporal(TemporalType.TIMESTAMP)
    private Date releaseDate;

    /**
     * Type of the software. It can be PROGRAM, GAME or EXTENSION
     */
    @NotNull
    private SoftwareType softwareType;

    /**
     * List with the offers of the software.
     */
    @OneToMany(mappedBy = "software", fetch = EAGER, cascade = ALL)
    private Set<Offer> offers;

    /**
     * List of all the wishes this software is on
     */
    @OneToMany(mappedBy = "software", fetch=EAGER, cascade=ALL)
    private Set<Wish> wishList;

    /**
     * Parent software. This attribute is only needed when the SoftwareType is
     * EXTENSION and it refers to the software extended by this one.
     */
    @ManyToOne
    private Software parentSoftware;

    /**
     * @return id of the software
     */
    public Integer getSoftwareId() {
        return softwareId;
    }

    /**
     * @param softwareId the id to set
     */
    public void setSoftwareId(Integer softwareId) {
        this.softwareId = softwareId;
    }

    /**
     * @return name of the software
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
     * @return publisher of the software
     */
    public String getPublisher() {
        return publisher;
    }

    /**
     * @param publisher the publisher to set
     */
    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    /**
     * @return description of the software
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return image of the software
     */
    public byte[] getImage() {
        return image;
    }

    /**
     * @param image the image to set
     */
    public void setImage(byte[] image) {
        this.image = image;
    }

    /**
     * @return release date of the software
     */
    public Date getReleaseDate() {
        return releaseDate;
    }

    /**
     * @param releaseDate the releaseDate to set
     */
    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    /**
     * @return type of the software
     */
    public SoftwareType getSoftwareType() {
        return softwareType;
    }

    /**
     * @param softwareType the softwareType to set
     */
    public void setSoftwareType(SoftwareType softwareType) {
        this.softwareType = softwareType;
    }

    /**
     * @return offers the software is in
     */
    public Set<Offer> getOffers() {
        return offers;
    }

    /**
     * @param offers the offers to set
     */
    public void setOffers(Set<Offer> offers) {
        this.offers = offers;
    }

    /**
     * @return wishes the software is in
     */
    @XmlTransient
    public Set<Wish> getWishList() {
        return wishList;
    }

    /**
     * @param wishList the wishList to set
     */
    public void setWishList(Set<Wish> wishList) {
        this.wishList = wishList;
    }

    /**
     * @return parent software of the software
     */
    public Software getParentSoftware() {
        return parentSoftware;
    }

    /**
     * @param parentSoftware the parentSoftware to set
     */
    public void setParentSoftware(Software parentSoftware) {
        this.parentSoftware = parentSoftware;
    }
    
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + Objects.hashCode(this.softwareId);
        hash = 59 * hash + Objects.hashCode(this.name);
        hash = 59 * hash + Objects.hashCode(this.publisher);
        hash = 59 * hash + Objects.hashCode(this.description);
        hash = 59 * hash + Objects.hashCode(this.image);
        hash = 59 * hash + Objects.hashCode(this.releaseDate);
        hash = 59 * hash + Objects.hashCode(this.softwareType);
        hash = 59 * hash + Objects.hashCode(this.offers);
        hash = 59 * hash + Objects.hashCode(this.wishList);
        hash = 59 * hash + Objects.hashCode(this.parentSoftware);
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
        final Software other = (Software) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.publisher, other.publisher)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (!Objects.equals(this.softwareId, other.softwareId)) {
            return false;
        }
        if (!Objects.equals(this.image, other.image)) {
            return false;
        }
        if (!Objects.equals(this.releaseDate, other.releaseDate)) {
            return false;
        }
        if (this.softwareType != other.softwareType) {
            return false;
        }
        if (!Objects.equals(this.offers, other.offers)) {
            return false;
        }
        if (!Objects.equals(this.wishList, other.wishList)) {
            return false;
        }
        if (!Objects.equals(this.parentSoftware, other.parentSoftware)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Software{" + "softwareId=" + softwareId + ", name=" + name + ", publisher=" + publisher + ", description=" + description + ", image=" + image + ", releaseDate=" + releaseDate + ", softwareType=" + softwareType + ", offers=" + offers + ", wishList=" + wishList + ", parentSoftware=" + parentSoftware + '}';
    }
}
