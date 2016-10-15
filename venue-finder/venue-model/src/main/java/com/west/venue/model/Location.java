package com.west.venue.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by westp on 15/10/2016.
 */
public class Location {

    private String lattitude;
    private String longitude;
    private String address;
    private String crossStreet;


    @JsonProperty("lat")
    public String getLattitude() {
        return lattitude;
    }

    public void setLattitude(String lattitude) {
        this.lattitude = lattitude;
    }

    @JsonProperty("lng")
    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCrossStreet() {
        return crossStreet;
    }

    public void setCrossStreet(String crossStreet) {
        this.crossStreet = crossStreet;
    }
}
