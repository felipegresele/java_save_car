package com.saveCar.SaveCar.controller;

import com.saveCar.SaveCar.entity.Carro;
import com.saveCar.SaveCar.entity.dto.UpdateCarroDTO;
import com.saveCar.SaveCar.entity.dto.ResponseCarroDTO;
import com.saveCar.SaveCar.service.CarroService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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

    @GetMapping("/{id}")
    public ResponseEntity<Carro> buscarPorId(@PathVariable Long id){
        Carro carro = service.buscarPorId(id);
        return ResponseEntity.ok().body(carro);
    }

    @PostMapping
    public ResponseEntity<Carro> save(@RequestBody ResponseCarroDTO dto) {
        Carro carro = service.salvarCarro(dto);
            return ResponseEntity.status(201).body(carro);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Carro> update(@RequestBody UpdateCarroDTO dto,
                                        @PathVariable Long id) {
        Carro carroAtualizado = service.editarCarro(id, dto);
        return ResponseEntity.status(200).body(carroAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        service.excluirCarro(id);
        return ResponseEntity.ok("Carro excluido com sucesso, ID: " + id);
    }

}
