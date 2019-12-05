/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package equipo2_crudapp_server.entities;

import java.io.Serializable;
import java.sql.Blob;
import java.util.Date;
import java.util.Objects;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**
 * User entity
 * @author Diego Corral
 */
@Entity
@Table( name = "user", schema = "equipo2crudappdb")
public class User implements Serializable{
    
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
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastPasswordChange;
    
    /**
     * The last time the user logged in
     */
    @NotNull
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
     * A list with all the software wishes of the user
     */
    @OneToMany
    private Set<Wish> wishList;

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.userId);
        hash = 29 * hash + Objects.hashCode(this.login);
        hash = 29 * hash + Objects.hashCode(this.password);
        hash = 29 * hash + Objects.hashCode(this.fullName);
        hash = 29 * hash + Objects.hashCode(this.email);
        hash = 29 * hash + Objects.hashCode(this.lastPasswordChange);
        hash = 29 * hash + Objects.hashCode(this.lastLogin);
        hash = 29 * hash + Objects.hashCode(this.privilege);
        hash = 29 * hash + Objects.hashCode(this.status);
        hash = 29 * hash + Objects.hashCode(this.wishList);
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
        if (!Objects.equals(this.wishList, other.wishList)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "User{" + "userId=" + userId + ", login=" + login + ", password=" + password + ", fullName=" + fullName + ", email=" + email + ", lastPasswordChange=" + lastPasswordChange + ", lastLogin=" + lastLogin + ", privilege=" + privilege + ", status=" + status + ", wishList=" + wishList + '}';
    }

    
    
}
