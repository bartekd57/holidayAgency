package com.travel_agency.mapper;

import com.travel_agency.dto.OrderoDTO;
import com.travel_agency.model.order.Ordero;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OrderMapper {

    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    OrderoDTO orderDTO(Ordero ordero);
    Ordero dtoToOrder(OrderoDTO orderoDTO);

}
