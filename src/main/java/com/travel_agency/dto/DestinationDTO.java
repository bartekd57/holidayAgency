package com.travel_agency.dto;

import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode
public class DestinationDTO {
    private Long id;
    private String continentName;
    private String countryName;
    private String cityName;
    private String airportName;
    private List<HotelDTO> hotels;
    private List<TripDTO> trips = new ArrayList<>();

    public DestinationDTO(Long id, String continentName, String countryName, String cityName, String airportName, List<HotelDTO> hotels, List<TripDTO> trips) {
        this.id = id;
        this.continentName = continentName;
        this.countryName = countryName;
        this.cityName = cityName;
        this.airportName = airportName;
        this.hotels = hotels;
        this.trips = trips;
    }

    public DestinationDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContinentName() {
        return continentName;
    }

    public void setContinentName(String continentName) {
        this.continentName = continentName;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getAirportName() {
        return airportName;
    }

    public void setAirportName(String airportName) {
        this.airportName = airportName;
    }

    public List<HotelDTO> getHotels() {
        return hotels;
    }

    public void setHotels(List<HotelDTO> hotels) {
        this.hotels = hotels;
    }

    public List<TripDTO> getTrips() {
        return trips;
    }

    public void setTrips(List<TripDTO> trips) {
        this.trips = trips;
    }
}
