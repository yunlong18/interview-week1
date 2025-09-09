package com.example.interview.week1.service.impl;

import org.springframework.stereotype.Service;

import com.example.interview.week1.dto.RegistrationDTO;
import com.example.interview.week1.service.RegistrationService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class RegistrationServiceImpl implements RegistrationService {

    @Override
    public RegistrationDTO registerUser(RegistrationDTO registrationDTO) {
        log.info("Registering user: {}", registrationDTO);
        return registrationDTO;
    }

}
