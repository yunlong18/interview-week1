package com.example.interview.week1.controller.converter;

import org.mapstruct.Mapper;

import com.example.interview.week1.dto.RegistrationDTO;
import com.example.interview.week1.dto.UserDTO;
import com.example.interview.week1.entity.User;

@Mapper
public interface UserMapper {

    UserDTO toDto(User user);

    User toEntity(UserDTO userDTO);

    User toEntity(RegistrationDTO registrationDTO);
}
