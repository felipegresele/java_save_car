package com.saveCar.SaveCar.service;

import com.saveCar.SaveCar.entity.Carro;
import com.saveCar.SaveCar.entity.dto.CreateCarroDTO;
import com.saveCar.SaveCar.entity.dto.CarroUpdateDTO;
import com.saveCar.SaveCar.repository.CarroRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarroService {

    private CarroRepository carroRepository;

    public CarroService(CarroRepository carroRepository) {
        this.carroRepository = carroRepository;
    }

    public List<Carro> buscarTodos() {
        return carroRepository.findAll();
    }

    public Carro buscarPorId(Long id) {
        Optional<Carro> carro = carroRepository.findById(id);
        return carro.orElse(null);
    }

    //Passando CreateCarroDTO nos parametros pois a funcao precisa receber os novos dados para poder criar um novo Carro
    public Carro salvarCarro(CreateCarroDTO dto) {
        Carro carro = new Carro();

        carro.setMarca(dto.marca());
        carro.setModelo(dto.modelo());
        carro.setAno(dto.ano());
        carro.setNovo(dto.novo());

        return carroRepository.save(carro);
    }

    public Carro editarCarro(Long id, CarroUpdateDTO carroAtualizado) {
        Carro carro = carroRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ID: " + id + " não encontrado, carro não existe"));

        //Atualizado dados do carro ja existente com o novo carro
        carro.setMarca(carroAtualizado.getMarca());
        carro.setModelo(carroAtualizado.getModelo());
        carro.setAno(carroAtualizado.getAno());
        carro.setNovo(carroAtualizado.isNovo());

        return carroRepository.save(carro);
    }

    public ResponseEntity<String> excluirCarro(Long id) {
        Carro carro = carroRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ID: " + id + " não encontrado, carro não existe"));
        carroRepository.delete(carro);
        return ResponseEntity.status(200).body("ID: " + id + " Excluido com sucesso!");
    }

}
