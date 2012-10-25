/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package health.database.models;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Leon
 */
@Entity
@Table(name = "users")
@NamedQueries({
    @NamedQuery(name = "Users.findAll", query = "SELECT u FROM Users u"),
    @NamedQuery(name = "Users.findByLoginID", query = "SELECT u FROM Users u WHERE u.loginID = :loginID"),
    @NamedQuery(name = "Users.findByUpdated", query = "SELECT u FROM Users u WHERE u.updated = :updated"),
    @NamedQuery(name = "Users.findByCreatedTime", query = "SELECT u FROM Users u WHERE u.createdTime = :createdTime"),
    @NamedQuery(name = "Users.findByLanguage", query = "SELECT u FROM Users u WHERE u.language = :language"),
    @NamedQuery(name = "Users.findByTimezone", query = "SELECT u FROM Users u WHERE u.timezone = :timezone"),
    @NamedQuery(name = "Users.findByEmail", query = "SELECT u FROM Users u WHERE u.email = :email"),
    @NamedQuery(name = "Users.findByUserAvatarid", query = "SELECT u FROM Users u WHERE u.userAvatarid = :userAvatarid"),
    @NamedQuery(name = "Users.findByBirthday", query = "SELECT u FROM Users u WHERE u.birthday = :birthday"),
    @NamedQuery(name = "Users.findByGender", query = "SELECT u FROM Users u WHERE u.gender = :gender")})
public class Users implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "loginID")
    private String loginID;
    @Column(name = "Updated")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updated;
    @Basic(optional = false)
    @Column(name = "CreatedTime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdTime;
    @Basic(optional = false)
    @Lob
    @Column(name = "password")
    private String password;
    @Lob
    @Column(name = "parentID")
    private String parentID;
    @Lob
    @Column(name = "groupID")
    private String groupID;
    @Column(name = "Language")
    private String language;
    @Column(name = "timezone")
    private String timezone;
    @Lob
    @Column(name = "screenname")
    private String screenname;
    @Basic(optional = false)
    @Column(name = "email")
    private String email;
    @Column(name = "userAvatar_id")
    private Integer userAvatarid;
    @Column(name = "birthday")
    private String birthday;
    @Basic(optional = false)
    @Column(name = "gender")
    private String gender;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "users")
    private UserDetails userDetails;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "users")
    private UserAvatar userAvatar;

    public Users() {
    }

    public Users(String loginID) {
        this.loginID = loginID;
    }

    public Users(String loginID, Date createdTime, String password, String email, String gender) {
        this.loginID = loginID;
        this.createdTime = createdTime;
        this.password = password;
        this.email = email;
        this.gender = gender;
    }

    public String getLoginID() {
        return loginID;
    }

    public void setLoginID(String loginID) {
        this.loginID = loginID;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getParentID() {
        return parentID;
    }

    public void setParentID(String parentID) {
        this.parentID = parentID;
    }

    public String getGroupID() {
        return groupID;
    }

    public void setGroupID(String groupID) {
        this.groupID = groupID;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public String getScreenname() {
        return screenname;
    }

    public void setScreenname(String screenname) {
        this.screenname = screenname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getUserAvatarid() {
        return userAvatarid;
    }

    public void setUserAvatarid(Integer userAvatarid) {
        this.userAvatarid = userAvatarid;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }


    public UserDetails getUserDetails() {
		return userDetails;
	}

	public void setUserDetails(UserDetails userDetails) {
		this.userDetails = userDetails;
	}

	public UserAvatar getUserAvatar() {
		return userAvatar;
	}

	public void setUserAvatar(UserAvatar userAvatar) {
		this.userAvatar = userAvatar;
	}

	@Override
    public int hashCode() {
        int hash = 0;
        hash += (loginID != null ? loginID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Users)) {
            return false;
        }
        Users other = (Users) object;
        if ((this.loginID == null && other.loginID != null) || (this.loginID != null && !this.loginID.equals(other.loginID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "health.database.models.Users[loginID=" + loginID + "]";
    }

}
