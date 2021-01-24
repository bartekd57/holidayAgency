package com.travel_agency.repository;

import com.travel_agency.model.hotel.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HotelRepository extends JpaRepository<Hotel, Long> {
    List<Hotel> findByStandard(String standard);
}
