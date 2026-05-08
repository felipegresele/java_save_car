package com.saveCar.SaveCar.controller;

import com.saveCar.SaveCar.dto.auth.LoginRequestDTO;
import com.saveCar.SaveCar.dto.auth.RegisterRequestDTO;
import com.saveCar.SaveCar.service.AuthenticationService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationService authenticationService;

    public AuthController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/register")
    public void register(@RequestBody @Valid RegisterRequestDTO dto) {
        authenticationService.register(dto);
    }

    @PostMapping("/login")
    public void login(@RequestBody LoginRequestDTO dto) {
        authenticationService.login(dto);
    }

}
