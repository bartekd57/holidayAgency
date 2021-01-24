package com.travel_agency.service;

import com.travel_agency.dto.AddressDTO;
import com.travel_agency.mapper.AddressMapper;
import com.travel_agency.model.address.Address;
import com.travel_agency.model.user.User;
import com.travel_agency.repository.AddressRepository;
import com.travel_agency.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class AddressService {

    AddressRepository addressRepository;
    UserRepository userRepository;

    @Autowired
    public AddressService(AddressRepository addressRepository, UserRepository userRepository) {
        this.addressRepository = addressRepository;
        this.userRepository = userRepository;
    }

    public AddressDTO createNewAddress(String road, Long roadNumber, String postalCode,
                                       String city, String country, Long userId){
        Address address = new Address();
        setAddressFields(address,road,roadNumber,postalCode,city,country);
        User user = findUserById(userId);
        address.setUser(user);
        addressRepository.save(address);
        return AddressMapper.INSTANCE.addressToDTO(address);
    }

    public AddressDTO editAddress(Long addressId,String road, Long roadNumber, String postalCode,
                                  String city, String country){
        Address address = addressRepository.findById(addressId).orElseThrow(NoSuchElementException::new);
        setAddressFields(address,road,roadNumber,postalCode,city,country);
        addressRepository.save(address);
        return AddressMapper.INSTANCE.addressToDTO(address);
    }

    private User findUserById(Long id){
        return userRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }
    private void setAddressFields(Address address,String road, Long roadNumber, String postalCode,
                                  String city, String country){
        address.setRoad(road);
        address.setRoadNumber(roadNumber);
        address.setPostalCode(postalCode);
        address.setCity(city);
        address.setCountry(country);
    }











}
