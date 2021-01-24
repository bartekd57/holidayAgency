package com.travel_agency.model.hotel;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.travel_agency.model.destination.Destination;
import com.travel_agency.model.trip.Trip;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "hotel")
public class Hotel {

    @Id
    @GeneratedValue
    private Long id;

    @NotEmpty
    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    private HotelStandardEnum standard;

    private String location;

    private String description;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name="destination_id")
    @JsonIgnore
    private Destination destination;

    @ManyToMany(fetch = FetchType.EAGER,
            cascade = {CascadeType.ALL})
    @JoinTable(name = "hotel_trip",
            joinColumns = @JoinColumn(name = "hotel_id"),
            inverseJoinColumns = @JoinColumn(name = "trip_id"))
    @JsonIgnore
    private List<Trip> trips = new ArrayList<>();

    public Hotel(Long id, @NotEmpty String name, HotelStandardEnum standard, String location, String description, Destination destination, List<Trip> trips) {
        this.id = id;
        this.name = name;
        this.standard = standard;
        this.location = location;
        this.description = description;
        this.destination = destination;
        this.trips = trips;
    }

    public Hotel() {
    }

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

    public Destination getDestination() {
        return destination;
    }

    public void setDestination(Destination destination) {
        this.destination = destination;
    }

    public List<Trip> getTrips() {
        return trips;
    }

    public void setTrips(List<Trip> trips) {
        this.trips = trips;
    }
}
