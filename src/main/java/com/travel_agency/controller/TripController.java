package com.travel_agency.controller;

import com.travel_agency.dto.HotelDTO;
import com.travel_agency.dto.TripDTO;
import com.travel_agency.model.trip.TripAlimentationEnum;
import com.travel_agency.model.trip.TripTypeEnum;
import com.travel_agency.service.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
        return "tripsByType";

    }



    @GetMapping(value = "/tripsByAlimentation/{alimentation}")
    public String getTripByAlimentation(@PathVariable String alimentation, Model model) {
        TripAlimentationEnum alimentationEnum = TripAlimentationEnum.valueOf(alimentation);
        List<TripDTO> tripsByTripType = tripService.getTripByAlimentation(alimentationEnum);

        model.addAttribute("tripsByTypes", tripsByTripType);
        model.addAttribute("tripType", alimentationEnum.name());

        return "tripsByType";

    }


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
    @GetMapping(value="/counter/trip/{id}", produces = "application/json")
    Integer getCounterForTripWithId(@PathVariable Long id) {
        return tripService.getCounterForTripWithId(id);
    }

}
