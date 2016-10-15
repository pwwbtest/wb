package com.west.venue.finder.service;

import com.west.venue.model.Venue;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;

/**
 * Created by westp on 15/10/2016.
 */
public class VenueFinderService implements VenueFinderAPI {

    @Value("${venue_finder_endpoint}")
    private String venueServiceEndpoint;

    public List<Venue> getVenuesNearLocation(String oAuthToken, String location, int radiusMetres) {

        return null;
    }
}
