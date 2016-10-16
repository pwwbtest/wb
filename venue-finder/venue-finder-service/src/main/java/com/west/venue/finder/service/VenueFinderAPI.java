package com.west.venue.finder.service;

import com.west.venue.model.Venue;

import java.util.List;

/**
 * Created by westp on 15/10/2016.
 */
public interface VenueFinderAPI {

    public List<Venue> getVenuesNearLocation(String oAuthToken, String location, int radiusMetres) throws VenueFinderServiceException;
    public List<Venue> getVenuesNearLocation(String oAuthToken, String location, int radiusMetres, int limit) throws VenueFinderServiceException;
}
