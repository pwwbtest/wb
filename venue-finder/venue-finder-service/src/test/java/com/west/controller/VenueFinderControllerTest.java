package com.west.controller;

import com.west.venue.model.Location;
import com.west.venue.model.Venue;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by westp on 16/10/2016.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class VenueFinderControllerTest {

    private static final String VALID_PUBLIC_OAUTH_TOKEN = "T3EEDQMOYVDGVB5LNXNZLKDT5PXUHIW2RHGY4N0QDB5GON0V";

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Before
    public void before() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(wac)
                .build();
    }

    @Test
    public void testFindVenuesUnauthorised() throws Exception {

        String oauthToken = "abc";
        String location = "london";
        int radiusMetres = 1000;
        int limit = 1;

        this.mockMvc.perform(get("/venue")
                .param("oauth_token", oauthToken)
                .param("location", location)
                .param("radius", Integer.toString(radiusMetres))
                .param("limit", Integer.toString(limit))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void testFindVenuesBadRequestWhenEmptyLocation() throws Exception {

        String oauthToken = VALID_PUBLIC_OAUTH_TOKEN;
        String location = "";
        int radiusMetres = 1000;
        int limit = 1;

        this.mockMvc.perform(get("/venue")
                .param("oauth_token", oauthToken)
                .param("location", location)
                .param("radius", Integer.toString(radiusMetres))
                .param("limit", Integer.toString(limit))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void testFindVenuesBadRequestWhenZeroRadius() throws Exception {

        String oauthToken = VALID_PUBLIC_OAUTH_TOKEN;
        String location = "london";
        int radiusMetres = 0;
        int limit = 1;

        this.mockMvc.perform(get("/venue")
                .param("oauth_token", oauthToken)
                .param("location", location)
                .param("radius", Integer.toString(radiusMetres))
                .param("limit", Integer.toString(limit))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void testFindVenuesBadRequestWhenNegativeRadius() throws Exception {

        String oauthToken = VALID_PUBLIC_OAUTH_TOKEN;
        String location = "london";
        int radiusMetres = -1;
        int limit = 1;

        this.mockMvc.perform(get("/venue")
                .param("oauth_token", oauthToken)
                .param("location", location)
                .param("radius", Integer.toString(radiusMetres))
                .param("limit", Integer.toString(limit))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }


    @Test
    public void testFindVenuesBadRequestWhenZeroLimit() throws Exception {

        String oauthToken = VALID_PUBLIC_OAUTH_TOKEN;
        String location = "london";
        int radiusMetres = 100;
        int limit = 0;

        this.mockMvc.perform(get("/venue")
                .param("oauth_token", oauthToken)
                .param("location", location)
                .param("radius", Integer.toString(radiusMetres))
                .param("limit", Integer.toString(limit))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void testFindVenuesBadRequestWhenNegativeLimit() throws Exception {

        String oauthToken = VALID_PUBLIC_OAUTH_TOKEN;
        String location = "london";
        int radiusMetres = 100;
        int limit = -1;

        this.mockMvc.perform(get("/venue")
                .param("oauth_token", oauthToken)
                .param("location", location)
                .param("radius", Integer.toString(radiusMetres))
                .param("limit", Integer.toString(limit))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }


    @Test
    public void testFindVenues() throws Exception {

        String oauthToken = VALID_PUBLIC_OAUTH_TOKEN;
        String location = "london";
        int radiusMetres = 1000;
        int limit = 1;

        this.mockMvc.perform(get("/venue")
                .param("oauth_token", oauthToken)
                .param("location", location)
                .param("radius", Integer.toString(radiusMetres))
                .param("limit", Integer.toString(limit))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }


    private List<Venue> getKnownResponse() {
        List<Venue> venues = new ArrayList<>();

        Venue venue = new Venue();
        venue.setId("1");
        venue.setName("name");

        Location location = new Location();
        location.setLattitude("lat");
        location.setLongitude("long");
        location.setAddress("address");
        location.setCrossStreet("crossstreet");

        venue.setLocation(location);

        venues.add(venue);

        return venues;
    }

}
