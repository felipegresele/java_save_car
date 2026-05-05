package com.saveCar.SaveCar.service;

import com.saveCar.SaveCar.entity.Carro;
import com.saveCar.SaveCar.entity.dto.CarroMapper;
import com.saveCar.SaveCar.entity.dto.ResponseCarroDTO;
import com.saveCar.SaveCar.entity.dto.UpdateCarroDTO;
import com.saveCar.SaveCar.infra.CarNotFoundException;
import com.saveCar.SaveCar.repository.CarroRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarroService {

    private CarroRepository carroRepository;
    private CarroMapper carroMapper;

    public CarroService(CarroRepository carroRepository, CarroMapper carroMapper) {
        this.carroRepository = carroRepository;
        this.carroMapper = carroMapper;
    }

    public List<Carro> buscarTodos() {
        return carroRepository.findAll();
    }

    public ResponseEntity<Optional<Carro>> buscarPorId(Long id) {
        Optional<Carro> carro = carroRepository.findById(id);

        if (carro.isEmpty()) {
            throw new CarNotFoundException("ID: " + id + " não encontrado, carro não existe");
        }

        return ResponseEntity.ok().body(carro);
    }

    //Passando CreateCarroDTO nos parametros pois a funcao precisa receber os novos dados para poder criar um novo Carro
    public Carro salvarCarro(ResponseCarroDTO dto) {
        Carro carro = carroMapper.toEntity(dto);
        return carroRepository.save(carro);
    }

    public Carro editarCarro(Long id, UpdateCarroDTO carroAtualizado) {
        Carro carro = carroRepository.findById(id)
                .orElseThrow(() -> new CarNotFoundException("ID: " + id + " não encontrado, carro não existe"));

        carroMapper.update(carroAtualizado, carro);

        return carroRepository.save(carro);
    }

    public ResponseEntity<String> excluirCarro(Long id) {
        Carro carro = carroRepository.findById(id)
                .orElseThrow(() -> new CarNotFoundException("ID: " + id + " não encontrado, carro não existe"));
        carroRepository.delete(carro);
        return ResponseEntity.status(200).body("ID: " + id + " Excluido com sucesso!");
    }

}
