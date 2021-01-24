package com.travel_agency.weather_checker;

import java.io.Serializable;
import java.util.List;

public class WeatherTemplate implements Serializable {


    private List<Main> main;
    private List<Weather> weather;

    public List<Main> getMain() {
        return main;
    }

    public void setMain(List<Main> main) {
        this.main = main;
    }

    public List<Weather> getWeather() {
        return weather;
    }

    public void setWeather(List<Weather> weather) {
        this.weather = weather;
    }
}
