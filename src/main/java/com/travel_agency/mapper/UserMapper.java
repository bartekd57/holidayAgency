package com.travel_agency.mapper;

import com.travel_agency.model.user.User;
import com.travel_agency.security.DTO.UserDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {

    static UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDTO userDto(User user);
    User dtoToUser(UserDTO userDto);

}
