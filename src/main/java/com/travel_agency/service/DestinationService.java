package com.travel_agency.service;

import com.travel_agency.dto.DestinationDTO;
import com.travel_agency.dto.HotelDTO;
import com.travel_agency.dto.TripDTO;
import com.travel_agency.mapper.DestinationMapper;
import com.travel_agency.mapper.HotelMapper;
import com.travel_agency.mapper.TripMapper;
import com.travel_agency.model.destination.Destination;
import com.travel_agency.model.hotel.Hotel;
import com.travel_agency.model.trip.Trip;
import com.travel_agency.repository.DestinationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class DestinationService {
    DestinationRepository destinationRepository;

    @Autowired
    public DestinationService(DestinationRepository destinationRepository) {
        this.destinationRepository = destinationRepository;
    }

    public DestinationDTO getDestinationById(Long id) {
        return destinationRepository.findById(id)
                .map(DestinationMapper.INSTANCE::destinationToDTO)
                .orElseThrow(NoSuchElementException::new);
    }

    public List<DestinationDTO> getAllDestinations() {
        return destinationRepository.findAll()
                .stream()
                .map(DestinationMapper.INSTANCE::destinationToDTO)
                .collect(Collectors.toList());
    }

    public List<HotelDTO> getHotelsForDestination(Long id) {
        List<Hotel> hotels = destinationRepository.findById(id)
                .map(Destination::getHotels)
                .orElseThrow(NoSuchElementException::new);
        return hotels.stream()
                .map(HotelMapper.INSTANCE::hotelDto)
                .collect(Collectors.toList());
    }

    public List<TripDTO> getTripsForDestination(Long id){
        List<Trip> trips = destinationRepository.findById(id)
                .map(Destination::getTrips)
                .orElseThrow(NoSuchElementException::new);
        return trips.stream()
                .map(TripMapper.INSTANCE::tripToDto)
                .collect(Collectors.toList());
    }

    public void saveDestination(DestinationDTO destinationDTO){
        Destination destination = DestinationMapper.INSTANCE.dtoToDestination(destinationDTO);
        destinationRepository.save(destination);
    }

    public void editAndSaveDestination(Long id, DestinationDTO destinationDTO){
        Destination destination = destinationRepository.findById(id).orElseThrow(NoSuchElementException::new);
        destination.setContinent(destinationDTO.getContinentName());
        destination.setCountry(destinationDTO.getCountryName());
        destination.setCity(destinationDTO.getCityName());
        destination.setAirport(destinationDTO.getAirportName());
        destinationRepository.save(destination);
    }

    public void deleteDestinationById(Long id){
        Destination destination = destinationRepository.findById(id).orElseThrow(NoSuchElementException::new);
        destinationRepository.delete(destination);
    }
}
