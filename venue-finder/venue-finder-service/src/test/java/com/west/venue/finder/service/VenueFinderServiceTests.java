package com.west.venue.finder.service;

import com.west.config.SpringTestConfig;
import com.west.venue.model.Contact;
import com.west.venue.model.Location;
import com.west.venue.model.Venue;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by westp on 15/10/2016.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes= SpringTestConfig.class)
public class VenueFinderServiceTests {

    private static final String PUBLIC_OAUTH_TOKEN = "T3EEDQMOYVDGVB5LNXNZLKDT5PXUHIW2RHGY4N0QDB5GON0V";

    @Autowired
    private VenueFinderService venueFinderService;


    // OAUTH token tests

    @Test(expected=VenueFinderServiceException.class)
    public void testGetVenuesNearLocationNullOauthToken() throws VenueFinderServiceException {
        String locationToSearchFor = "london";
        int radiusMetres = 800;
        List<Venue> venues = venueFinderService.getVenuesNearLocation(null, locationToSearchFor, radiusMetres);
    }

    @Test(expected=VenueFinderServiceException.class)
    public void testGetVenuesNearLocationEmptyOauthToken() throws VenueFinderServiceException {
        String locationToSearchFor = "london";
        int radiusMetres = 800;
        List<Venue> venues = venueFinderService.getVenuesNearLocation("", locationToSearchFor, radiusMetres);
    }

    @Test(expected=VenueFinderServiceException.class)
    public void testGetVenuesNearLocationInvalidOauthToken() throws VenueFinderServiceException {
        String locationToSearchFor = "london";
        int radiusMetres = 800;
        List<Venue> venues = venueFinderService.getVenuesNearLocation("abc", locationToSearchFor, radiusMetres);
    }


    // Location tests

    @Test(expected=VenueFinderServiceException.class)
    public void testGetVenuesNearLocationNullLocation() throws VenueFinderServiceException {
        String locationToSearchFor = null;
        int radiusMetres = 800;
        List<Venue> venues = venueFinderService.getVenuesNearLocation(PUBLIC_OAUTH_TOKEN, locationToSearchFor, radiusMetres);
    }

    @Test(expected=VenueFinderServiceException.class)
    public void testGetVenuesNearLocationEmptyLocation() throws VenueFinderServiceException {
        String locationToSearchFor = "";
        int radiusMetres = 800;
        List<Venue> venues = venueFinderService.getVenuesNearLocation(PUBLIC_OAUTH_TOKEN, locationToSearchFor, radiusMetres);
    }


    // Radius tests

    @Test(expected=VenueFinderServiceException.class)
    public void testGetVenuesNearLocationZeroRadius() throws VenueFinderServiceException {
        String locationToSearchFor = "london";
        int radiusMetres = 0;
        List<Venue> venues = venueFinderService.getVenuesNearLocation(PUBLIC_OAUTH_TOKEN, locationToSearchFor, radiusMetres);
    }

    @Test(expected=VenueFinderServiceException.class)
    public void testGetVenuesNearLocationNegativeRadius() throws VenueFinderServiceException {
        String locationToSearchFor = "london";
        int radiusMetres = -1;
        List<Venue> venues = venueFinderService.getVenuesNearLocation(PUBLIC_OAUTH_TOKEN, locationToSearchFor, radiusMetres);
    }


    // Limit tests

    @Test(expected=VenueFinderServiceException.class)
    public void testGetVenuesNearLocationZeroLimit() throws VenueFinderServiceException {
        String locationToSearchFor = "london";
        int radiusMetres = 1000;
        int limit = 0;
        List<Venue> venues = venueFinderService.getVenuesNearLocation(PUBLIC_OAUTH_TOKEN, locationToSearchFor, radiusMetres, limit);
    }

    @Test(expected=VenueFinderServiceException.class)
    public void testGetVenuesNearLocationNegativeLimit() throws VenueFinderServiceException {
        String locationToSearchFor = "london";
        int radiusMetres = 1000;
        int limit = -1;
        List<Venue> venues = venueFinderService.getVenuesNearLocation(PUBLIC_OAUTH_TOKEN, locationToSearchFor, radiusMetres, limit);
    }



    @Test
    public void testGetVenuesNearLocationWithDefaultLimit() throws VenueFinderServiceException {
        String locationToSearchFor = "london";
        int radiusMetres = 800;
        List<Venue> venues = venueFinderService.getVenuesNearLocation(PUBLIC_OAUTH_TOKEN, locationToSearchFor, radiusMetres);
        assertEquals(VenueFinderService.DEFAULT_LIMIT, venues.size());
    }

    @Test
    public void testGetVenuesNearLocationWithExplicitLimit() throws VenueFinderServiceException {
        String locationToSearchFor = "london";
        int radiusMetres = 800;
        int limit = 3;
        List<Venue> venues = venueFinderService.getVenuesNearLocation(PUBLIC_OAUTH_TOKEN, locationToSearchFor, radiusMetres, limit);
        assertEquals(limit, venues.size());
    }



    @Test
    public void testGetVenuesNearLocationResponsePopulated() throws VenueFinderServiceException {

        String locationToSearchFor = "chicago";
        int radiusMetres = 800;

            List<Venue> venues = venueFinderService.getVenuesNearLocation(PUBLIC_OAUTH_TOKEN, locationToSearchFor, radiusMetres);
            assertNotNull(venues);
            assertTrue(venues.size() > 0);

            Location location = null;
            Contact contact = null;
            for (Venue venue : venues) {

                assertNotNull(venue.getId());
                assertNotNull(venue.getName());
                assertNotNull(venue.getLocation());

                location = venue.getLocation();
                // assertNotNull(location.getAddress()); not always populated
                //assertNotNull(location.getCrossStreet()); not always populated
                assertNotNull(location.getLongitude());
                assertNotNull(location.getLattitude());

                contact = venue.getContact();
                // assertNotNull(contact.getPhone()); not always populated

                //TODO mock the response so it is always populated for the test
            }
        }

}
