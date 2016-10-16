package com.west.venue.finder.service;

/**
 * Created by westp on 15/10/2016.
 */
public class VenueFinderServiceException extends Exception {

    private int statusCode;

    public VenueFinderServiceException(int statusCode, String message) {
        super(message);
        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return statusCode;
    }
}
