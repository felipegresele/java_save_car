package com.saveCar.SaveCar.service;

import com.saveCar.SaveCar.entity.Carro;
import com.saveCar.SaveCar.entity.dto.CarroMapper;
import com.saveCar.SaveCar.entity.dto.ResponseCarroDTO;
import com.saveCar.SaveCar.entity.dto.UpdateCarroDTO;
import com.saveCar.SaveCar.infra.CarNotFoundException;
import com.saveCar.SaveCar.infra.InvalidCarDataException;
import com.saveCar.SaveCar.repository.CarroRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    public Page<Carro> buscarTodos(Pageable pageable) {
        return carroRepository.findAll(pageable);
    }

    public Carro buscarPorId(Long id) {
        return carroRepository.findById(id).orElseThrow(() -> new CarNotFoundException("ID: " + id + " não encontrado, carro não existe"));
    }

    //Passando CreateCarroDTO nos parametros pois a funcao precisa receber os novos dados para poder criar um novo Carro
    public Carro salvarCarro(ResponseCarroDTO dto) {
        if (dto.marca() == null || dto.marca().isBlank()
                || dto.modelo() == null || dto.modelo().isBlank()
                || dto.ano() == null || dto.ano() <= 1960
                || dto.novo() == null
        ) {
            throw new InvalidCarDataException("Os campos estão inválidos");
        }

        //Cria se os dados estiverem valídos
        Carro carro = carroMapper.toEntity(dto);
        return carroRepository.save(carro);
    }

    public Carro editarCarro(Long id, UpdateCarroDTO carroAtualizado) {
        Carro carro = carroRepository.findById(id)
                .orElseThrow(() -> new CarNotFoundException("ID: " + id + " não encontrado, carro não existe"));

        if (carroAtualizado.getModelo() == null || carroAtualizado.getModelo().isBlank()
                || carroAtualizado.getMarca() == null || carroAtualizado.getMarca().isBlank()
                || carroAtualizado.getAno() == null || carroAtualizado.getAno() <= 1960
                || carroAtualizado.getNovo() == null
        ) {
            throw new InvalidCarDataException("Os campos estão inválidos");
        }

        //Só cria depois de validar todos os atributos
        carroMapper.update(carroAtualizado, carro);

        return carroRepository.save(carro);
    }

    public void excluirCarro(Long id) {
        Carro carro = carroRepository.findById(id)
                .orElseThrow(() -> new CarNotFoundException("ID: " + id + " não encontrado, carro não existe"));
        carroRepository.delete(carro);
    }

}
