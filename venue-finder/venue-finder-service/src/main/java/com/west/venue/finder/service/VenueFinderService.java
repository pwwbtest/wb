package com.west.venue.finder.service;

import com.west.venue.model.CallResponse;
import com.west.venue.model.Venue;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.http.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by westp on 15/10/2016.
 */
public class VenueFinderService implements VenueFinderAPI {

    @Value("${venue_finder_endpoint}")
    private String venueServiceEndpoint;

    public List<Venue> getVenuesNearLocation(String oAuthToken, String location, int radiusMetres) throws VenueFinderServiceException {

        if(radiusMetres < 1) {
            throw new VenueFinderServiceException("Invalid radiusMetres. Must be positive integer.");
        }

        Map<String, String> queryParams = new HashMap<String, String>();
        queryParams.put("intent","browse");
        queryParams.put("near", location);
        queryParams.put("radius", Integer.toString(radiusMetres));
        queryParams.put("oauth_token", oAuthToken);
        queryParams.put("v", "20161015");

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<?> requestEntity = new HttpEntity<Object>(headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<CallResponse> resp = null;
        List<Venue> venues = null;

        try {
            resp = restTemplate.exchange(venueServiceEndpoint, HttpMethod.GET, requestEntity, CallResponse.class, queryParams);


            if(resp.getStatusCode().is2xxSuccessful()) {
                venues = resp.getBody().getResponse().getVenues();
            } else {
                throw new VenueFinderServiceException("Request failed: " + resp.getStatusCode());
            }

        } catch(HttpClientErrorException e) {
            throw new VenueFinderServiceException("Request failed: " + e.getMessage());
        }

        return venues;
    }
}
