package com.travel_agency.model.trip;

public enum TripAlimentationEnum {
    AI("All inclusive"),
    BB("Bed and Breakfast"),
    HB("Half Board"),
    FB("Full Board"),
    SC("Full Board");


    String description;

    TripAlimentationEnum() {
    }

    TripAlimentationEnum(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
