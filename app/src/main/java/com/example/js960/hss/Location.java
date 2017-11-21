package com.example.js960.hss;

/**
 * Created by js960 on 2017-11-21.
 */

public class Location {

    private double lat;
    private double lon;

    //Initialize Location
    public Location(double lat, double lon)
    {
        this.lat = lat;
        this.lon = lon;
    }
    //default Constructor
    public Location() {

    }

    //Getter and Setter of Location : Lat, Lot
    public double getLat() {
        return lat;
    }
    public void setLat(float lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(float lon) {
        this.lon = lon;
    }



}
