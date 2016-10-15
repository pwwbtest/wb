package com.west.venue.finder.service;

import com.west.config.SpringTestConfig;
import com.west.venue.model.Venue;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by westp on 15/10/2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes= SpringTestConfig.class)
public class VenueFinderServiceTests {

    private static final String PUBLIC_OAUTH_TOKEN = "T3EEDQMOYVDGVB5LNXNZLKDT5PXUHIW2RHGY4N0QDB5GON0V";

    @Autowired
    private VenueFinderService venueFinderService;

    @Test
    public void testGetVenuesNearLocation() {

        String location = "Chicago";
        int radiusMetres = 800;

        List<Venue> venues = venueFinderService.getVenuesNearLocation(PUBLIC_OAUTH_TOKEN, location, radiusMetres);
        assertNotNull(venues);
        assertTrue(venues.size() > 0);

        for(Venue venue: venues) {
            assertNotNull(venue.getLocation());
        }
    }
}
