package com.saveCar.SaveCar.controller;

import com.saveCar.SaveCar.dto.carro.CreateCarroDTO;
import com.saveCar.SaveCar.entity.CarroEntity;
import com.saveCar.SaveCar.dto.carro.UpdateCarroDTO;
import com.saveCar.SaveCar.dto.carro.ResponseCarroDTO;
import com.saveCar.SaveCar.service.CarroService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/carros")
public class CarroController {

    private CarroService service;

    public CarroController(CarroService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<Page<CarroEntity>> getAll(Pageable pageable) {
        //Qual a pagina = page
        //Qual o tamanho de cada pagina = size
        //Qual a ordem e pelo o que vai ordenar = order

        //Dessa forma o back controla
        //pageable = PageRequest.of(0,10, Sort.by("ano").descending());

        //Se não passar isso quem controla a ordenação é o front
        return ResponseEntity.ok(service.buscarTodos(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarroEntity> buscarPorId(@PathVariable Long id){
        CarroEntity carro = service.buscarPorId(id);
        return ResponseEntity.ok().body(carro);
    }

    @PostMapping
    public ResponseEntity<CarroEntity> save(@RequestBody CreateCarroDTO dto) {
        CarroEntity carro = service.salvarCarro(dto);
            return ResponseEntity.status(201).body(carro);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CarroEntity> update(@RequestBody UpdateCarroDTO dto,
                                              @PathVariable Long id) {
        CarroEntity carroAtualizado = service.editarCarro(id, dto);
        return ResponseEntity.status(200).body(carroAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        service.excluirCarro(id);
        return ResponseEntity.ok("Carro excluido com sucesso, ID: " + id);
    }

}
