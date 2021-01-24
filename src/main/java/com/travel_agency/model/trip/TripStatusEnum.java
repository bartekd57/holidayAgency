package com.travel_agency.model.trip;

public enum TripStatusEnum {
    ACTIVE("Aktywna"), EXPIRED("Wygaszona"), PROMOTED("Promocyjna");


    String description;

    TripStatusEnum(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
