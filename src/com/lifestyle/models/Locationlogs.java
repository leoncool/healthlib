/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lifestyle.models;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Leon
 */
@Entity
@Table(name = "locationlogs")
@XmlRootElement
public class Locationlogs implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "userID")
    private String userID;
    @Basic(optional = false)
    @Column(name = "lat")
    private double lat;
    @Basic(optional = false)
    @Column(name = "lon")
    private double lon;
    @Basic(optional = false)
    @Column(name = "elevationV")
    private double elevation;
    @Basic(optional = false)
    @Column(name = "accuracy")
    private double accuracy;
    @Basic(optional = false)
    @Column(name = "bearing")
    private double bearing;
    @Basic(optional = false)
    @Column(name = "speed")
    private double speed;
    @Basic(optional = false)
    @Column(name = "datetime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datetime;

    public Locationlogs() {
    }

    public Locationlogs(Integer id) {
        this.id = id;
    }

    public Locationlogs(String userID, Date datetime,double lat, double lon, double elevation, double accuracy, double bearing, double speed) {
        this.id = null;
        this.userID = userID;
        this.lat = lat;
        this.lon = lon;
        this.elevation = elevation;
        this.accuracy = accuracy;
        this.bearing = bearing;
        this.speed = speed;
        this.datetime=datetime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public double getElevation() {
        return elevation;
    }

    public void setElevation(double elevation) {
        this.elevation = elevation;
    }

    public double getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(double accuracy) {
        this.accuracy = accuracy;
    }

    public double getBearing() {
        return bearing;
    }

    public void setBearing(double bearing) {
        this.bearing = bearing;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public Date getDatetime() {
        return datetime;
    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
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
        if (!(object instanceof Locationlogs)) {
            return false;
        }
        Locationlogs other = (Locationlogs) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.lifestyle.models.Locationlogs[ id=" + id + " ]";
    }
}
