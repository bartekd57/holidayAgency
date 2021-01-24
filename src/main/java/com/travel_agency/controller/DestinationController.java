package com.travel_agency.controller;

import com.travel_agency.dto.DestinationDTO;
import com.travel_agency.dto.HotelDTO;
import com.travel_agency.dto.TripDTO;
import com.travel_agency.service.DestinationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class DestinationController {

    DestinationService destinationService;

    @Autowired
    public DestinationController(DestinationService destinationService) {
        this.destinationService = destinationService;
    }

    @ResponseBody
    @RequestMapping("destinations")
    public List<DestinationDTO> getAllDestinations(){
        return destinationService.getAllDestinations();
    }

    @ResponseBody
    @RequestMapping("/destination/{id}")
    public DestinationDTO getDestinationById(@PathVariable Long id){
        return destinationService.getDestinationById(id);
    }

    @GetMapping("/deleteDestination")
    public void deleteDestination(@RequestParam Long id){
        destinationService.deleteDestinationById(id);
    }

    @PostMapping("/editDestination")
    public void editDestionation(@RequestParam Long id, @RequestBody DestinationDTO destinationDTO){
        destinationService.editAndSaveDestination(id, destinationDTO);
    }

    @ResponseBody
    @GetMapping("/destination/{id}/trips")
    public List<TripDTO> getTripsForDestination(@PathVariable Long id){
        return destinationService.getTripsForDestination(id);
    }

    @PostMapping("/saveDestination")
    public void saveDestination( @RequestBody DestinationDTO destinationDTO){
        destinationService.saveDestination(destinationDTO);
    }

    @ResponseBody
    @GetMapping("/destination/{id}/hotels")
    public List<HotelDTO> getHotelsForDestination(@PathVariable Long id){
        return destinationService.getHotelsForDestination(id);
    }
}
