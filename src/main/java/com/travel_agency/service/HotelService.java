package com.travel_agency.service;

import com.travel_agency.dto.HotelDTO;
import com.travel_agency.dto.TripDTO;
import com.travel_agency.mapper.HotelMapper;
import com.travel_agency.mapper.TripMapper;
import com.travel_agency.model.hotel.Hotel;
import com.travel_agency.repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class HotelService {

    HotelRepository hotelRepository;

    @Autowired
    public HotelService(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }


    public List<HotelDTO> getAllHotels(){    //uwazam ze ten zapis ze streamem a bez petli jest zgodny z aktualnym clean code
        return hotelRepository.findAll()
                .stream()
                .map(HotelMapper.INSTANCE::hotelDto)
                .collect(Collectors.toList());
    }

    public HotelDTO getHotelById(Long id){
        return hotelRepository.findById(id)
                .map(HotelMapper.INSTANCE::hotelDto)
                .orElseThrow(NoSuchElementException::new);
    }

    public List<TripDTO> getTripsForHotel(Long id){
        Hotel hotel = hotelRepository.findById(id).orElseThrow(NoSuchElementException::new);
        return hotel.getTrips().stream().map(TripMapper.INSTANCE::tripToDto).collect(Collectors.toList());
    }

    public List<HotelDTO> getHotelsByStandard(String standard){
        return hotelRepository.findByStandard(standard)
                .stream()
                .map(HotelMapper.INSTANCE::hotelDto)
                .collect(Collectors.toList());
    }

    public void saveHotel(HotelDTO hotelDTO){
        Hotel hotel = HotelMapper.INSTANCE.dtoToHotel(hotelDTO);
        hotelRepository.save(hotel);
    }

    public void editAndSaveHotel(Long id, HotelDTO hotelDTO){
        Hotel hotel = hotelRepository.findById(id).orElseThrow(NoSuchElementException::new);
        hotel.setName(hotelDTO.getName());
        hotel.setStandard(hotelDTO.getStandard());
        hotel.setLocation(hotelDTO.getLocation());
        hotel.setDescription(hotelDTO.getDescription());
        hotelRepository.save(hotel);
    }

    public void deleteHotelById(Long id){
        Hotel hotel = hotelRepository.findById(id).orElseThrow(NoSuchElementException::new);
        hotelRepository.delete(hotel);
    }
}
