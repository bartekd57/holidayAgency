package com.travel_agency.controller;

import com.travel_agency.dto.TripDTO;
import com.travel_agency.model.trip.TripAlimentationEnum;
import com.travel_agency.model.trip.TripStatusEnum;
import com.travel_agency.service.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class HomeController {

    TripService tripService;

    @Autowired
    public HomeController(TripService tripService) {
        this.tripService = tripService;
    }

    @GetMapping("/")
    public String homePage(Model model){

        List<TripStatusEnum> collects = Arrays.stream(TripStatusEnum.values()).collect(Collectors.toList());

        List<TripAlimentationEnum> alimentationTypes = Arrays.stream(TripAlimentationEnum.values())
                .collect(Collectors.toList());


        List<TripDTO> trips = tripService.getAllTrips();
        model.addAttribute("trips", trips);
        model.addAttribute("collects", collects);
        model.addAttribute("alimentationTypes", alimentationTypes);
        return "index";

    }


}
