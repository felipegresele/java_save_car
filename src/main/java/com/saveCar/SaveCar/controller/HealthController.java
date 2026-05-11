package com.saveCar.SaveCar.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@Tag(name = "Ping", description = "Recurso responsavel por ping de endpoint")
public class HealthController {

    @GetMapping("/status")
    public ResponseEntity<String> health() {
        return ResponseEntity.status(200).body("API Funcionando");
    }

}
