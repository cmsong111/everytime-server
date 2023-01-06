package com.untouchable.everytime.Service;

import com.untouchable.everytime.Config.JwtConfig;
import com.untouchable.everytime.DTO.UserDTO;
import com.untouchable.everytime.Entity.UserEntity;
import com.untouchable.everytime.Repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    UserRepository userRepository;
    ModelMapper modelMapper;

    JwtConfig jwtConfig;

    PasswordEncoder encoder;


    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder encoder, JwtConfig jwtConfig,ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.encoder = encoder;
        this.jwtConfig = jwtConfig;
        this.modelMapper = modelMapper;
    }

    public String login(String ID, String PWD) {
        UserEntity userEntity = userRepository.findById(ID).get();

        if (encoder.matches(PWD, userEntity.getPWD())) {
            return jwtConfig.createToken(userEntity);
            //return modelMapper.map(userEntity, UserDTO.class);
        }
        return null;
    }

    public UserDTO register(UserDTO userDTO) {
        String password = encoder.encode(userDTO.getPWD());
        userDTO.setPWD(password);
        userDTO.setPoint(0L);
        UserEntity userEntity = modelMapper.map(userDTO, UserEntity.class);
        System.out.println(userEntity.toString());
        userRepository.save(userEntity);
        return modelMapper.map(userEntity, UserDTO.class);
    }

    public Optional<UserDTO> updateUser(UserDTO userDTO) {
        UserEntity userEntity = modelMapper.map(userDTO, UserEntity.class);
        userRepository.save(userEntity);
        return Optional.of(modelMapper.map(userEntity, UserDTO.class));
    }

    public void deleteUser(String ID) {
        userRepository.deleteById(ID);
    }

    public Optional<UserDTO> getUserById(String ID) {
        Optional<UserEntity> userEntity = userRepository.findById(ID);
        if (userEntity.isPresent()){
            return Optional.of(modelMapper.map(userEntity.get(), UserDTO.class));
        }
        return Optional.empty();
    }

}