package com.travel_agency.mapper;

import com.travel_agency.dto.HotelDTO;
import com.travel_agency.model.hotel.Hotel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface HotelMapper {

    static HotelMapper INSTANCE = Mappers.getMapper(HotelMapper.class);

    HotelDTO hotelDto(Hotel hotel);
    Hotel dtoToHotel(HotelDTO hotelDto);

}
