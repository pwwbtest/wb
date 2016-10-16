package com.west.venue.finder.service;

import com.west.venue.model.FourSquareResponse;
import com.west.venue.model.Venue;
import com.west.venue.model.VenueFinderServiceResponse;
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

    public static final int DEFAULT_LIMIT = 10;

    @Value("${venue_finder_endpoint}")
    private String venueServiceEndpoint;

    public List<Venue> getVenuesNearLocation(String oAuthToken, String location, int radiusMetres) throws VenueFinderServiceException {
        return getVenuesNearLocation(oAuthToken, location, radiusMetres, DEFAULT_LIMIT);
    }

    @Override
    public List<Venue> getVenuesNearLocation(String oAuthToken, String location, int radiusMetres, int limit) throws VenueFinderServiceException {

        validateLimit(limit);
        validateRadius(radiusMetres);

        Map<String, String> queryParams = getQueryParamMap(oAuthToken, location, radiusMetres, limit);
        HttpEntity<?> requestEntity = getRequestEntity();

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<FourSquareResponse> resp = null;
        List<Venue> venues = null;
        VenueFinderServiceResponse venueFinderServiceResponse = null;

        try {
            resp = restTemplate.exchange(venueServiceEndpoint, HttpMethod.GET, requestEntity, FourSquareResponse.class, queryParams);

            if(resp.getStatusCode().is2xxSuccessful()) {
                venues = resp.getBody().getResponse().getVenues();
                venueFinderServiceResponse = new VenueFinderServiceResponse(venues);
            } else {
                throw new VenueFinderServiceException(resp.getStatusCodeValue(), "Request failed: " + resp.toString());
            }

        } catch(HttpClientErrorException e) {
            throw new VenueFinderServiceException(e.getRawStatusCode(), "Request failed: " + e.getMessage());
        }

        return venues;
    }


    private HttpEntity<?> getRequestEntity() {

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        return new HttpEntity<Object>(headers);
    }

    private Map<String, String> getQueryParamMap(String oAuthToken, String location, int radiusMetres, int limit) {
        Map<String, String> queryParams = new HashMap<String, String>();
        queryParams.put("intent","browse");
        queryParams.put("near", location);
        queryParams.put("radius", Integer.toString(radiusMetres));
        queryParams.put("limit", Integer.toString(limit));
        queryParams.put("oauth_token", oAuthToken);
        queryParams.put("v", "20161015");
        return queryParams;
    }

    private void validateRadius(int radiusMetres) throws VenueFinderServiceException {
        if(radiusMetres < 1) {
            throw new VenueFinderServiceException(HttpStatus.BAD_REQUEST.value(), "Invalid radiusMetres. Must be positive integer.");
        }
    }

    private void validateLimit(int limit) throws VenueFinderServiceException {
        if(limit < 1) {
            throw new VenueFinderServiceException(HttpStatus.BAD_REQUEST.value(), "Invalid limit. Must be positive integer.");
        }
    }
}
