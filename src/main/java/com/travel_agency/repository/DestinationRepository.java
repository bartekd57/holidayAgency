package com.travel_agency.repository;

import com.travel_agency.model.destination.Destination;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DestinationRepository extends JpaRepository<Destination, Long> {
}
