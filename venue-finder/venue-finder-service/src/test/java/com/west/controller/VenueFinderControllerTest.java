package com.west.controller;

import com.west.venue.finder.service.VenueFinderService;
import com.west.venue.model.Location;
import com.west.venue.model.Venue;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.*;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by westp on 16/10/2016.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class VenueFinderControllerTest {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Mock
    private VenueFinderService venueFinderService;

    @Before
    public void before() {
        MockitoAnnotations.initMocks(this);
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

        given(this.venueFinderService.getVenuesNearLocation(oauthToken, location, radiusMetres, limit)).willReturn(getKnownResponse());
        this.mockMvc.perform(get("/venue")
                .param("oauth_token", oauthToken)
                .param("location", location)
                .param("radius", Integer.toString(radiusMetres))
                .param("limit", Integer.toString(limit))
                .accept(MediaType.parseMediaType(MediaType.APPLICATION_JSON_UTF8_VALUE)))
                .andExpect(status().isUnauthorized())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));
    }


    @Test
    public void testFindVenues() throws Exception {

        String oauthToken = "T3EEDQMOYVDGVB5LNXNZLKDT5PXUHIW2RHGY4N0QDB5GON0V";
        String location = "london";
        int radiusMetres = 1000;
        int limit = 1;

        given(this.venueFinderService.getVenuesNearLocation(oauthToken, location, radiusMetres, limit)).willReturn(getKnownResponse());
        this.mockMvc.perform(get("/venue")
                .param("oauth_token", oauthToken)
                .param("location", location)
                .param("radius", Integer.toString(radiusMetres))
                .param("limit", Integer.toString(limit)).
                        accept(MediaType.parseMediaType(MediaType.APPLICATION_JSON_UTF8_VALUE)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));
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
