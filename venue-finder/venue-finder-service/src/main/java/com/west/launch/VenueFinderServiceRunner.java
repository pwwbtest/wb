package com.west.launch;

import com.west.config.SpringConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

/**
 * Created by westp on 15/10/2016.
 */
@SpringBootApplication
@Import(SpringConfig.class)
public class VenueFinderServiceRunner {

    public static void main(String args[]) {
        SpringApplication.run(VenueFinderServiceRunner.class, args);
    }
}
