package com.west.controller;

import com.west.venue.finder.service.VenueFinderService;
import com.west.venue.finder.service.VenueFinderServiceException;
import com.west.venue.model.Venue;
import com.west.venue.model.VenueFinderServiceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by westp on 16/10/2016.
 */
@RestController
public class VenueFinderController {

    private VenueFinderService venueFinderService;

    @Autowired
    public VenueFinderController(VenueFinderService venueFinderService) {
        this.venueFinderService = venueFinderService;
    }

    @RequestMapping(value="/venue", method= RequestMethod.GET)
    public ResponseEntity<VenueFinderServiceResponse> findVenues(@RequestParam(value="oauth_token") String oauthToken,
                                 @RequestParam(value="location") String location,
                                 @RequestParam(value="radius") int radiusMetres,
                                 @RequestParam(value="limit", defaultValue = "10") int limit) throws VenueFinderServiceException {

        VenueFinderServiceResponse venueFinderServiceResponse = null;
        ResponseEntity<VenueFinderServiceResponse> responseEntity = null;
        List<Venue> venues = null;
        try {
            venues = venueFinderService.getVenuesNearLocation(oauthToken, location, radiusMetres, limit);
            venueFinderServiceResponse =  new VenueFinderServiceResponse(venues);
            responseEntity = ResponseEntity.ok().body(venueFinderServiceResponse);
        } catch(VenueFinderServiceException e) {
            venueFinderServiceResponse = new VenueFinderServiceResponse(e.getStatusCode(), "Request failed: " + e.getMessage());
            responseEntity = ResponseEntity.status(e.getStatusCode()).body(venueFinderServiceResponse);
        }

        return responseEntity;
    }


}
