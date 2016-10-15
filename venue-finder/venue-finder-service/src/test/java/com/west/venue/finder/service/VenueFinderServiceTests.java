package com.west.venue.finder.service;

import com.west.config.SpringTestConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


import static org.junit.Assert.assertNotNull;

/**
 * Created by westp on 15/10/2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes= SpringTestConfig.class)
public class VenueFinderServiceTests {

    @Autowired
    private VenueFinderService venueFinderService;

    @Test
    public void testGetVenuesNearLocation() {

        String oAuthToken = null;
        String location = "Chicago";
        int radiusMetres = 800;

        assertNotNull(venueFinderService.getVenuesNearLocation(oAuthToken, location, radiusMetres));
    }
}
