package com.west.venue.model;

import java.util.List;

/**
 * Created by westp on 15/10/2016.
 * This is partially populated only. More data is available in the feed and can be incorporated here if required.
 */
public class Response {

    private List<Venue> venues;

    public List<Venue> getVenues() {
        return venues;
    }

    public void setVenues(List<Venue> venues) {
        this.venues = venues;
    }
}
