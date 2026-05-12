package com.saveCar.SaveCar.controller;

import com.saveCar.SaveCar.dto.auth.LoginRequestDTO;
import com.saveCar.SaveCar.dto.auth.RegisterRequestDTO;
import com.saveCar.SaveCar.dto.auth.TokenResponseDTO;
import com.saveCar.SaveCar.service.AuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@Tag(name = "Autenticação", description = "Recurso responsavel pela autenticação para acesso")
public class AuthController {

    private final AuthenticationService authenticationService;

    public AuthController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @Operation(summary = "Registrar usuário", description = "Metódo responseval por registrar um usuário")
    @ApiResponse(responseCode = "201", description = "Usuário registrado com sucesso!")
    @PostMapping("/register")
    public void register(@RequestBody @Valid RegisterRequestDTO dto) {
        authenticationService.register(dto);
    }

    @Operation(summary = "Login de usuário", description = "Metódo responseval por fazer login do usuário e retornar token")
    @ApiResponse(responseCode = "200", description = "Usuário logado com sucesso!")
    @PostMapping("/login")
    public ResponseEntity<TokenResponseDTO> login(@RequestBody LoginRequestDTO dto) {
        return ResponseEntity.ok(authenticationService.login(dto));
    }

}
