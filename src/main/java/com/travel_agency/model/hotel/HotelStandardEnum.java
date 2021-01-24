package com.travel_agency.model.hotel;

public enum HotelStandardEnum {
    ONE_STAR("1-gwiazdkowy"),
    TWO_STAR("2-gwiazdkowy"),
    THREE_STAR("3-gwiazdkowy"),
    FOUR_STAR("4-gwiazdkowy"),
    FIVE_STAR("5-gwiazdkowy");

    String desciprtion;

    HotelStandardEnum(String desciprtion) {
        this.desciprtion = desciprtion;
    }

    public String getDesciprtion() {
        return desciprtion;
    }
}
