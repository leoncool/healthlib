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
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author Leon
 */
@Entity
@Table(name = "user_avatar")
@NamedQueries({
    @NamedQuery(name = "UserAvatar.findAll", query = "SELECT u FROM UserAvatar u"),
    @NamedQuery(name = "UserAvatar.findByHash", query = "SELECT u FROM UserAvatar u WHERE u.hash = :hash"),
    @NamedQuery(name = "UserAvatar.findByUrl", query = "SELECT u FROM UserAvatar u WHERE u.url = :url"),
    @NamedQuery(name = "UserAvatar.findById", query = "SELECT u FROM UserAvatar u WHERE u.id = :id")})
public class UserAvatar implements Serializable {
    private static final long serialVersionUID = 1L;
    @Column(name = "hash")
    private String hash;
    @Basic(optional = false)
    @Column(name = "url")
    private String url;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private String id;
    @JoinColumn(name = "loginID", referencedColumnName = "loginID")
    @OneToOne(optional = false)
    private Users users;

    public UserAvatar() {
    }

    public UserAvatar(String id) {
        this.id = id;
    }

    public UserAvatar(String id, String url) {
        this.id = id;
        this.url = url;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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
        if (!(object instanceof UserAvatar)) {
            return false;
        }
        UserAvatar other = (UserAvatar) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "health.database.models.UserAvatar[id=" + id + "]";
    }

}
