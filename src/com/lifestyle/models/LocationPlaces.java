/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lifestyle.models;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Leon
 */
@Entity
@Table(name = "location_places")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "LocationPlaces.findAll", query = "SELECT l FROM LocationPlaces l"),
    @NamedQuery(name = "LocationPlaces.findById", query = "SELECT l FROM LocationPlaces l WHERE l.id = :id"),
    @NamedQuery(name = "LocationPlaces.findByLat", query = "SELECT l FROM LocationPlaces l WHERE l.lat = :lat"),
    @NamedQuery(name = "LocationPlaces.findByLon", query = "SELECT l FROM LocationPlaces l WHERE l.lon = :lon"),
    @NamedQuery(name = "LocationPlaces.findByRadius", query = "SELECT l FROM LocationPlaces l WHERE l.radius = :radius"),
    @NamedQuery(name = "LocationPlaces.findByName", query = "SELECT l FROM LocationPlaces l WHERE l.name = :name")})
public class LocationPlaces implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;
    @Basic(optional = false)
    @Column(name = "lat")
    private double lat;
    @Basic(optional = false)
    @Column(name = "lon")
    private double lon;
    @Basic(optional = false)
    @Column(name = "radius")
    private double radius;
    @Column(name = "name")
    private String name;
    @Column(name = "userID")
    private String userID;
    @Column(name = "category")
    private String category;

    public LocationPlaces() {
    }

    public LocationPlaces(int id) {
        this.id = id;
    }

    public LocationPlaces(double lat, double lon, double radius, String name, String userID) {
//        this.id = id;
        this.lat = lat;
        this.lon = lon;
        this.radius = radius;
        this.name = name;
        this.userID = userID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "com.lifestyle.models.LocationPlaces[ id=" + id + " ]";
    }
}
