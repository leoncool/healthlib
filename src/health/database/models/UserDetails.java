/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package health.database.models;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Leon
 */
@Entity
@Table(name = "user_details")
@NamedQueries({
    @NamedQuery(name = "UserDetails.findAll", query = "SELECT u FROM UserDetails u"),
    @NamedQuery(name = "UserDetails.findByHeight", query = "SELECT u FROM UserDetails u WHERE u.height = :height"),
    @NamedQuery(name = "UserDetails.findByHometown", query = "SELECT u FROM UserDetails u WHERE u.hometown = :hometown"),
    @NamedQuery(name = "UserDetails.findByWeight", query = "SELECT u FROM UserDetails u WHERE u.weight = :weight"),
    @NamedQuery(name = "UserDetails.findById", query = "SELECT u FROM UserDetails u WHERE u.id = :id")})
public class UserDetails implements Serializable {
    private static final long serialVersionUID = 1L;
    @Column(name = "height")
    private Double height;
    @Column(name = "hometown")
    private Integer hometown;
    @Column(name = "weight")
    private Double weight;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private String id;
    @JoinColumn(name = "loginID", referencedColumnName = "loginID")
    @ManyToOne(optional = false)
    private Users users;

    public UserDetails() {
    }

    public UserDetails(String id) {
        this.id = id;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public Integer getHometown() {
        return hometown;
    }

    public void setHometown(Integer hometown) {
        this.hometown = hometown;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UserDetails)) {
            return false;
        }
        UserDetails other = (UserDetails) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "health.database.models.UserDetails[id=" + id + "]";
    }

}
