package com.west.venue.model;


import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

/**
 * Created by westp on 16/10/2016.
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class VenueFinderServiceResponse {

    private int statusCode;
    private String statusMessage;
    private List<Venue> venues;

    public VenueFinderServiceResponse(List<Venue> venues) {
        this.statusCode = 200;
        this.statusMessage = "OK";
        this.venues = venues;
    }

    public VenueFinderServiceResponse(int statusCode, String statusMessage) {
        this.statusCode = statusCode;
        this.statusMessage = statusMessage;
    }

    public List<Venue> getVenues() {
        return venues;
    }

    public void setVenues(List<Venue> venues) {
        this.venues = venues;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }
}
