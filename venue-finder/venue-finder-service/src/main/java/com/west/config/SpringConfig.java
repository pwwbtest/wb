package com.west.config;

import com.west.venue.finder.service.VenueFinderService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by westp on 15/10/2016.
 */
@Configuration
public class SpringConfig {

    @Bean
    public VenueFinderService getVenueFinderService() {
        return new VenueFinderService();
    }
}
