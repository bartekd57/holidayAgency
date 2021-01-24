package com.travel_agency.mapper;

import com.travel_agency.dto.TripDTO;
import com.travel_agency.model.trip.Trip;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TripMapper {

    static TripMapper INSTANCE = Mappers.getMapper(TripMapper.class);

    TripDTO tripToDto(Trip trip);
    Trip dtoToTrip(TripDTO tripDto);

}
