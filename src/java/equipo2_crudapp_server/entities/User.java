/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package equipo2_crudapp_server.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * User entity
 *
 * @author Diego Corral
 */
@Entity
@Table(name = "user", schema = "equipo2crudappdb")
@NamedQueries({
    @NamedQuery(name = "findAllUsers",
            query = "SELECT a FROM User a ORDER BY a.id DESC")
    ,
        @NamedQuery(name = "checkUserPassword",
            query = "SELECT a FROM User a WHERE a.login = :login AND a.password = :password")
})
@XmlRootElement
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * The user id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer userId;

    /**
     * The login of the user
     */
    @NotNull
    private String login;

    /**
     * The password of the user account
     */
    @NotNull
    private String password;

    /**
     * The full name of the user
     */
    @NotNull
    private String fullName;

    /**
     * The email of the user
     */
    @NotNull
    private String email;

    /**
     * The date of the las password change
     */
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastPasswordChange;

    /**
     * The last time the user logged in
     */
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastLogin;

    /**
     * The privilege of the user. It can be USER or ADMIN
     */
    @NotNull
    private UserPrivilege privilege;

    /**
     * The user status. It can be ENABLED or DISABLED
     */
    @NotNull
    private UserStatus status;

    /**
     * Profile image of the user
     */
    @Lob
    @Basic(fetch=EAGER)
    private byte[] image;

    /**
     * A list with all the software wishes of the user
    @OneToMany(mappedBy = "user", fetch = EAGER, cascade=ALL)
    private Set<Wish> wishList;   */
    @OneToMany(mappedBy = "user", fetch = EAGER, cascade=ALL)
    private Set<Wish> wishList;

    @OneToMany(mappedBy = "user", fetch = EAGER, cascade = ALL)
    private List<Comment> comments;
    
    @OneToMany(mappedBy = "user", fetch = EAGER, cascade = ALL)
    private List<Offer> offers;
    
    /**
     * @return the userId
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * @param userId the userId to set
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * @return the login
     */
    public String getLogin() {
        return login;
    }

    /**
     * @param login the login to set
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the fullName
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * @param fullName the fullName to set
     */
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the lastPasswordChange
     */
    public Date getLastPasswordChange() {
        return lastPasswordChange;
    }

    /**
     * @param lastPasswordChange the lastPasswordChange to set
     */
    public void setLastPasswordChange(Date lastPasswordChange) {
        this.lastPasswordChange = lastPasswordChange;
    }

    /**
     * @return the lastLogin
     */
    public Date getLastLogin() {
        return lastLogin;
    }

    /**
     * @param lastLogin the lastLogin to set
     */
    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }

    /**
     * @return the privilege
     */
    public UserPrivilege getPrivilege() {
        return privilege;
    }

    /**
     * @param privilege the privilege to set
     */
    public void setPrivilege(UserPrivilege privilege) {
        this.privilege = privilege;
    }

    /**
     * @return the status
     */
    public UserStatus getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(UserStatus status) {
        this.status = status;
    }

    /**
     * @return the image
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
     * @return the wishList
     */
    public Set<Wish> getWishList() {
        return wishList;
    }

    /**
     * @param wishList the wishList to set
     */
    public void setWishList(Set<Wish> wishList) {
        this.wishList = wishList;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public List<Offer> getOffers() {
        return offers;
    }

    public void setOffers(List<Offer> offers) {
        this.offers = offers;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + Objects.hashCode(this.userId);
        hash = 17 * hash + Objects.hashCode(this.login);
        hash = 17 * hash + Objects.hashCode(this.password);
        hash = 17 * hash + Objects.hashCode(this.fullName);
        hash = 17 * hash + Objects.hashCode(this.email);
        hash = 17 * hash + Objects.hashCode(this.lastPasswordChange);
        hash = 17 * hash + Objects.hashCode(this.lastLogin);
        hash = 17 * hash + Objects.hashCode(this.privilege);
        hash = 17 * hash + Objects.hashCode(this.status);
        hash = 17 * hash + Objects.hashCode(this.image);
        hash = 17 * hash + Objects.hashCode(this.wishList);
        hash = 17 * hash + Objects.hashCode(this.comments);
        hash = 17 * hash + Objects.hashCode(this.offers);
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
        final User other = (User) obj;
        if (!Objects.equals(this.login, other.login)) {
            return false;
        }
        if (!Objects.equals(this.password, other.password)) {
            return false;
        }
        if (!Objects.equals(this.fullName, other.fullName)) {
            return false;
        }
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        if (!Objects.equals(this.userId, other.userId)) {
            return false;
        }
        if (!Objects.equals(this.lastPasswordChange, other.lastPasswordChange)) {
            return false;
        }
        if (!Objects.equals(this.lastLogin, other.lastLogin)) {
            return false;
        }
        if (this.privilege != other.privilege) {
            return false;
        }
        if (this.status != other.status) {
            return false;
        }
        if (!Objects.equals(this.image, other.image)) {
            return false;
        }
        if (!Objects.equals(this.wishList, other.wishList)) {
            return false;
        }
        if (!Objects.equals(this.comments, other.comments)) {
            return false;
        }
        if (!Objects.equals(this.offers, other.offers)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "User{" + "userId=" + userId + ", login=" + login + ", password=" + password + ", fullName=" + fullName + ", email=" + email + ", lastPasswordChange=" + lastPasswordChange + ", lastLogin=" + lastLogin + ", privilege=" + privilege + ", status=" + status + ", image=" + image + ", wishList=" + wishList + ", comments=" + comments + ", offers=" + offers + '}';
    }
}
