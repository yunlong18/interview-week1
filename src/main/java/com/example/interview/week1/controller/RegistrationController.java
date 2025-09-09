package com.example.interview.week1.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.interview.week1.dto.RegistrationDTO;
import com.example.interview.week1.service.RegistrationService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/registration")
public class RegistrationController {

    private final RegistrationService registrationService;

    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @PostMapping
    public RegistrationDTO registerUser(@Valid @RequestBody RegistrationDTO registrationDTO) {
        return registrationService.registerUser(registrationDTO);
    }

}
