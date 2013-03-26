/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lifestyle.output.models;

import java.io.Serializable;

/**
 *
 * @author Leon
 */
public class JsonLocationLog {

    private String time;
    private String lat;
    private String lon;
    private String accuracy;
    private String time_long;
    private String tag;

    public String getTime_long() {
        return time_long;
    }

    public void setTime_long(String time_long) {
        this.time_long = time_long;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public String getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(String accuracy) {
        this.accuracy = accuracy;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}