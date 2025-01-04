package com.untouchable.everytime.user.mapper;

import com.untouchable.everytime.user.DTO.SignUpRequest;
import com.untouchable.everytime.user.DTO.UserDTO;
import com.untouchable.everytime.user.domain.User;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
        value = "org.mapstruct.ap.MappingProcessor",
        date = "2024-07-22T02:38:31+0900",
        comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.2 (Oracle Corporation)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public User toEntity(SignUpRequest signUpRequest) {
        if (signUpRequest == null) {
            return null;
        }

        User.UserBuilder user = User.builder();

        user.id(signUpRequest.getId());
        user.password(signUpRequest.getPassword());
        user.name(signUpRequest.getName());
        user.nickname(signUpRequest.getNickname());
        user.email(signUpRequest.getEmail());

        return user.build();
    }

    @Override
    public UserDTO toDTO(User user) {
        if (user == null) {
            return null;
        }

        UserDTO userDTO = new UserDTO();

        return userDTO;
    }
}
