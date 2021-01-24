package com.travel_agency.mapper;

import com.travel_agency.dto.AddressDTO;
import com.travel_agency.model.address.Address;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AddressMapper {

    AddressMapper INSTANCE = Mappers.getMapper(AddressMapper.class);

    AddressDTO addressToDTO(Address address);
    Address dtoToAddress(AddressDTO addressDTO);


}
