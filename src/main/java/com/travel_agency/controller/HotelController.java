package com.travel_agency.controller;

import com.travel_agency.dto.HotelDTO;
import com.travel_agency.dto.TripDTO;
import com.travel_agency.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
public class HotelController {

    HotelService hotelService;

    @Autowired
    public HotelController(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    @ResponseBody
    @RequestMapping(value = "/hotels", produces="application/json")
    public List<HotelDTO> getAllHotels() {
        return hotelService.getAllHotels();
    }

    @ResponseBody
    @GetMapping("/hotels/{id}")
    public HotelDTO getHotelById(@PathVariable Long id){
        return hotelService.getHotelById(id);
    }

    @ResponseBody
    @GetMapping("/hotels/{standard}")
    public List<HotelDTO> getHotelsByStatus(@PathVariable String standard){
        return hotelService.getHotelsByStandard(standard);
    }

    @GetMapping("/deleteHotel")
    public void deleteHotel(@RequestParam Long id){
        hotelService.deleteHotelById(id);
    }

    @PostMapping("/editHotel")
    public void editHotel(@RequestParam Long id, @RequestBody HotelDTO hotelDTO){
        hotelService.editAndSaveHotel(id, hotelDTO);
    }

    @ResponseBody
    @GetMapping("/hotel/{id}/trips")
    public List<TripDTO> getTripsForHotelById(@PathVariable Long id){
        return hotelService.getTripsForHotel(id);
    }
}
