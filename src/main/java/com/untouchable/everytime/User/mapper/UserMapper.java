package com.untouchable.everytime.User.mapper;

import com.untouchable.everytime.User.DTO.SignUpRequest;
import com.untouchable.everytime.User.DTO.UserDTO;
import com.untouchable.everytime.User.Entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {


    User toEntity(SignUpRequest signUpRequest);

    UserDTO toDTO(User user);
}
