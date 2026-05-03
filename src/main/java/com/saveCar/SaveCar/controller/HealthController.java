package com.saveCar.SaveCar.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class HealthController {

    @GetMapping("/status")
    public ResponseEntity health() {
        return ResponseEntity.status(200).body("API Funcionando");
    }

}
