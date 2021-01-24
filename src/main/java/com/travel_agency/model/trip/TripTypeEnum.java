package com.travel_agency.model.trip;

public enum TripTypeEnum {
    SUMMER ("Wakacyjna"), WINTER("Zimowa"), ROUND_TRIP("Objazdowa"), LAST_MINUTE("Last minute");


    String description;

    TripTypeEnum(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
