package com.travel_agency.dto;

import com.travel_agency.model.hotel.HotelStandardEnum;
import com.travel_agency.model.trip.Trip;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class HotelDTO {

    private Long id;
    private String name;
    private HotelStandardEnum standard;
    private String location;
    private String description;
    private DestinationDTO destinationDTO;
    private List<Trip> trips = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HotelStandardEnum getStandard() {
        return standard;
    }

    public void setStandard(HotelStandardEnum standard) {
        this.standard = standard;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public DestinationDTO getDestinationDTO() {
        return destinationDTO;
    }

    public void setDestinationDTO(DestinationDTO destinationDTO) {
        this.destinationDTO = destinationDTO;
    }

    public List<Trip> getTrips() {
        return trips;
    }

    public void setTrips(List<Trip> trips) {
        this.trips = trips;
    }
}
