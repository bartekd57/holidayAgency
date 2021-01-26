package com.travel_agency.repository;

import com.travel_agency.dto.DestinationDTO;
import com.travel_agency.model.destination.Destination;
import com.travel_agency.model.trip.Trip;
import com.travel_agency.model.trip.TripAlimentationEnum;
import com.travel_agency.model.trip.TripTypeEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


public interface TripRepository extends JpaRepository<Trip, Long> {

    Optional<List<Trip>> findAllByTripType(TripTypeEnum type);
    Optional<List<Trip>> findAllByTripAlimentationEnum(TripAlimentationEnum alimentationEnum);

    Optional<List<Trip>> findAllByDateFromAfterAndDateToBeforeAndDestination_Continent(LocalDateTime dateFrom, LocalDateTime dateTo, String continent);

    Optional<List<Trip>> findAllByDateFromIsAfterAndDateToBeforeAndDestination_Continent(LocalDateTime dateFrom, LocalDateTime dateTo, String continent);


}
