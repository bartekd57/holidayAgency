package com.travel_agency.weather_checker;

import java.io.Serializable;

public class Weather implements Serializable {

    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
