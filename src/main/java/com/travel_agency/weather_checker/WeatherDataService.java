package com.travel_agency.weather_checker;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherDataService {


    private static final String WEATHER_URI = "http://api.openweathermap.org/data/2.5/weather?";

    @Value("${api.key}")
    private String apiKey;

    @Autowired
    private RestTemplate restTemplate;

    public WeatherTemplate getDataByCityId(String cityId) {
        String uri = WEATHER_URI + "q=" + cityId;
        return this.getWeatherResponse(uri);
    }

    public WeatherTemplate getDataByCityName(String cityName) {
        String uri = WEATHER_URI + "q=" + cityName + "&apiKey=" + apiKey ;
        return this.getWeatherResponse(uri);
    }


    private WeatherTemplate getWeatherResponse(String uri) {
        ResponseEntity<String> respString = restTemplate.getForEntity(uri, String.class);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
        WeatherTemplate resp = null;
        String strBody = null;
        if (respString.getStatusCodeValue() == 200) {
            strBody = respString.getBody();
        }
        try {
            resp = objectMapper.readValue(strBody, WeatherTemplate.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resp;

    }
}
