package com.travel_agency.mapper;

import com.travel_agency.dto.DestinationDTO;
import com.travel_agency.model.destination.Destination;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DestinationMapper {

    DestinationMapper INSTANCE = Mappers.getMapper(DestinationMapper.class);

    DestinationDTO destinationToDTO(Destination destination);
    Destination dtoToDestination(DestinationDTO destinationDTO);


}
