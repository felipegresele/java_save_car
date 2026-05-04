package com.saveCar.SaveCar.controller;

import com.saveCar.SaveCar.entity.Carro;
import com.saveCar.SaveCar.entity.dto.CarroUpdateDTO;
import com.saveCar.SaveCar.entity.dto.CreateCarroDTO;
import com.saveCar.SaveCar.service.CarroService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/carros")
public class CarroController {

    private CarroService service;

    public CarroController(CarroService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Carro>> getAll() {
        List<Carro> carros = service.buscarTodos();
        return ResponseEntity.ok().body(carros);
    }

    @PostMapping
    public ResponseEntity<Carro> save(@RequestBody CreateCarroDTO dto) {
        Carro carro = service.salvarCarro(dto);
            return ResponseEntity.status(201).body(carro);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Carro> update(@RequestBody CarroUpdateDTO dto,
                                        @PathVariable Long id) {
        Carro carroAtualizado = service.editarCarro(id, dto);
        return ResponseEntity.status(200).body(carroAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        return service.excluirCarro(id);
    }

}
