package com.travel_agency.configuration;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "com.travel_agency.repository")
//@EntityScan(basePackages = "com.travel_agency.model")
@EntityScan(basePackages= {"com.travel_agency.model.address","com.travel_agency.model.branch", "com.travel_agency.model.destination",
        "com.travel_agency.model.hotel", "com.travel_agency.model.order", "com.travel_agency.model.reservation",
        "com.travel_agency.model.trip", "com.travel_agency.model.user", "com.travel_agency.model.participant"})
public class SpringDataConfig {

}
