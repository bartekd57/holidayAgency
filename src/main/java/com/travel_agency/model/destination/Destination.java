package com.travel_agency.model.destination;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.travel_agency.model.hotel.Hotel;
import com.travel_agency.model.trip.Trip;
import com.travel_agency.weather_checker.WeatherTemplate;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "destination")
public class Destination {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    @Column(nullable = false)
    private String continent;

    @NotEmpty
    @Column(nullable = false)
    private String country;

    @NotEmpty
    @Column(nullable = false)
    private String city;

    @NotEmpty
    private String airport;

    @OneToMany(mappedBy = "destination",cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    @JsonIgnore
    private List<Hotel> hotels= new ArrayList<>();

    @OneToMany(mappedBy = "destination", cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    @JsonIgnore
    private List<Trip> trips = new ArrayList<>();

    private WeatherTemplate weatherTemplate;

    public Destination(Long id, @NotEmpty String continent, @NotEmpty String country, @NotEmpty String city, @NotEmpty String airport, List<Hotel> hotels, List<Trip> trips) {
        this.id = id;
        this.continent = continent;
        this.country = country;
        this.city = city;
        this.airport = airport;
        this.hotels = hotels;
        this.trips = trips;
    }

    public Destination() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContinent() {
        return continent;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAirport() {
        return airport;
    }

    public void setAirport(String airport) {
        this.airport = airport;
    }

    public List<Hotel> getHotels() {
        return hotels;
    }

    public void setHotels(List<Hotel> hotels) {
        this.hotels = hotels;
    }

    public List<Trip> getTrips() {
        return trips;
    }

    public void setTrips(List<Trip> trips) {
        this.trips = trips;
    }

    public WeatherTemplate getWeatherTemplate() {
        return weatherTemplate;
    }

    public void setWeatherTemplate(WeatherTemplate weatherTemplate) {
        this.weatherTemplate = weatherTemplate;
    }
}
