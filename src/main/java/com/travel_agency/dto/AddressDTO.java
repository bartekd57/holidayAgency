package com.travel_agency.dto;

import com.travel_agency.model.user.User;


public class AddressDTO {


    private Long id;
    private String road;
    private Long roadNumber;
    private String postalCode;
    private String city;
    private String country;
    private User user;

    public AddressDTO(Long id, String road, Long roadNumber, String postalCode, String city, String country, User user) {
        this.id = id;
        this.road = road;
        this.roadNumber = roadNumber;
        this.postalCode = postalCode;
        this.city = city;
        this.country = country;
        this.user = user;
    }

    public AddressDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoad() {
        return road;
    }

    public void setRoad(String road) {
        this.road = road;
    }

    public Long getRoadNumber() {
        return roadNumber;
    }

    public void setRoadNumber(Long roadNumber) {
        this.roadNumber = roadNumber;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
