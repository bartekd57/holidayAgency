package com.travel_agency.controller;

import com.travel_agency.dto.HotelDTO;
import com.travel_agency.dto.TripDTO;
import com.travel_agency.model.trip.Trip;
import com.travel_agency.model.trip.TripAlimentationEnum;
import com.travel_agency.model.trip.TripStatusEnum;
import com.travel_agency.model.trip.TripTypeEnum;
import com.travel_agency.service.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class TripController {

    TripService tripService;

    int count;

    @Autowired
    public TripController(TripService tripService) {
        this.tripService = tripService;
    }

    @ResponseBody
    @GetMapping("/trips")
    public List<TripDTO> getAllTrips() {
//        List<TripDTO> trips = tripService.getAllTrips();
//        model.addAttribute("tripsList", trips);
//        return "trips";
        return tripService.getAllTrips();
    }


    @GetMapping("/tripsByType")
    public String getTripByType(@RequestParam TripTypeEnum type, Model model) {
        List<TripDTO> tripsByTripType = tripService.getTripByTripType(type);
        model.addAttribute("tripsByTypes", tripsByTripType);
        model.addAttribute("tripType", type.name());
        tripService.setAttributesForMainPage(model);
        return "tripsByType";

    }


    @GetMapping(value = "/tripsByAlimentation/{alimentation}")
    public String getTripByAlimentation(@PathVariable String alimentation, Model model) {
        TripAlimentationEnum alimentationEnum = TripAlimentationEnum.valueOf(alimentation);
        List<TripDTO> tripsByTripType = tripService.getTripByAlimentation(alimentationEnum);

        model.addAttribute("tripsByTypes", tripsByTripType);
        model.addAttribute("tripType", alimentationEnum.name());
        tripService.setAttributesForMainPage(model);

        return "tripsByType";

    }

    @PostMapping(value = "/tripSearch")
    public String getTripsFromSearchBox(@RequestParam String start, @RequestParam String end,
                                        @RequestParam String continent, Model model) {
        LocalDateTime startDate = LocalDateTime.parse(start);
        LocalDateTime endDate = LocalDateTime.parse(end);

        List<TripDTO> tripBySearchBox = tripService.getTripBySearchBox(startDate, endDate, continent);
        model.addAttribute("tripsSearched", tripBySearchBox);
        tripService.setAttributesForMainPage(model);

        return "tripsSearched";
    }

    @GetMapping("/newTrip")
    public String createNewTrip(Model model) {
        List<TripTypeEnum> tripTypeEnums = Arrays.asList(TripTypeEnum.values());
        List<TripAlimentationEnum> tripAlimentationEnums = Arrays.asList(TripAlimentationEnum.values());
        Trip trip = new Trip();


        model.addAttribute("tripTypes", tripTypeEnums);
        model.addAttribute("tripAlimentations", tripAlimentationEnums);


        return "addTrip";

    }

    @PostMapping("/newTrip")
    public String addNewTrip(@RequestParam String start, @RequestParam String end, @RequestParam String adultPrice, @RequestParam String childPrice,
                             @RequestParam String type, @RequestParam String alimentation, @RequestParam String description,
                             @RequestParam Integer limit, @RequestParam String continent, @RequestParam String country,
                             @RequestParam String city, @RequestParam String airport, @RequestParam String url, Model model) {

        LocalDateTime startTime = LocalDateTime.parse(start);
        LocalDateTime endTime = LocalDateTime.parse(end);
        BigDecimal adultPriceTag = new BigDecimal(adultPrice);
        BigDecimal childPriceTag = new BigDecimal(childPrice);

        TripTypeEnum typeEnum = tripService.getTypeValueFromEnumName(type);
        TripAlimentationEnum alimentationEnum = tripService.getAlimentationValueFromEnumName(alimentation);

        tripService.createAndSaveNewTrip(startTime, endTime, adultPriceTag,
                childPriceTag, typeEnum, alimentationEnum, description,
                limit, url, continent, country, city, airport);


        model.addAttribute("message", "Pomyślnie utworzono nową wycieczkę");
        tripService.setAttributesForMainPage(model);

        return "message";
    }


//    @ResponseBody
//    @PostMapping(value = "/tripSearch", produces = "application/json")
//    public List<TripDTO> getTripsFromSearchBox(@RequestParam LocalDateTime start, @RequestParam LocalDateTime end,
//                                        @RequestParam String continent, Model model){
//        List<TripDTO> tripBySearchBox = tripService.getTripBySearchBox(start, end, continent);
//        model.addAttribute("trips", tripBySearchBox);
//
//        return tripBySearchBox;
//    }

//    @ResponseBody
//    @PostMapping(value = "/searchTrip", produces = "application/json")
//    public String getTripsFromSearchBox(@RequestParam LocalDateTime start, @RequestParam LocalDateTime end,
//                                        @RequestParam String continent, Model model){
//        List<TripDTO> tripBySearchBox = tripService.getTripBySearchBox(start, end, continent);
//        model.addAttribute("trips", tripBySearchBox);
//
//        return "xxxx";
//    }


//    @GetMapping(value = "/tripsByAlimentation")
//    public String getTripByAlimentation(@RequestParam TripAlimentationEnum alimentation, Model model) {
//        List<TripDTO> tripsByTripType = tripService.getTripByAlimentation(alimentation);
//
//        model.addAttribute("tripsByTypes", tripsByTripType);
//        model.addAttribute("tripType", alimentation.name());
//
//        return "tripsByType";
//
//    }


    @GetMapping(value = "/trip/{id}")
    public String getTripById(@PathVariable("id") Long id, Model model) {

        TripDTO tripById = tripService.getTripById(id);
        List<HotelDTO> hotelsForTrip = tripService.getHotelsForTripById(id);
        model.addAttribute("trip", tripById);
        model.addAttribute("hotelsForTrip", hotelsForTrip);
        return "tripDetails";
    }

    @ResponseBody
    @GetMapping(value = "/tripname/{id}", produces = "application/json")
    String getTripNameById(@PathVariable("id") Long id) {
        TripDTO tripDTO = tripService.getTripById(id);
        String tripName = tripDTO.getDescription();
        return tripName;
    }

    @ResponseBody
    @GetMapping(value = "/getTripsForUser/{id}", produces = "application/json")
    List<TripDTO> getTripsForUser(@PathVariable("id") Long id) {
        return tripService.getTripsForUser(id);
    }

    @ResponseBody
    @GetMapping(value = "/counter/trip/{id}", produces = "application/json")
    Integer getCounterForTripWithId(@PathVariable Long id) {
        return tripService.getCounterForTripWithId(id);
    }

}
