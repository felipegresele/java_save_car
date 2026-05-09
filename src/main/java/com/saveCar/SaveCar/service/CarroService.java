package com.saveCar.SaveCar.service;

import com.saveCar.SaveCar.dto.carro.CreateCarroDTO;
import com.saveCar.SaveCar.entity.CarroEntity;
import com.saveCar.SaveCar.mapper.CarroMapper;
import com.saveCar.SaveCar.dto.carro.ResponseCarroDTO;
import com.saveCar.SaveCar.dto.carro.UpdateCarroDTO;
import com.saveCar.SaveCar.infra.exceptions.CarNotFoundException;
import com.saveCar.SaveCar.infra.exceptions.InvalidCarDataException;
import com.saveCar.SaveCar.repository.CarroRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CarroService {

    private CarroRepository carroRepository;
    private CarroMapper carroMapper;

    public CarroService(CarroRepository carroRepository, CarroMapper carroMapper) {
        this.carroRepository = carroRepository;
        this.carroMapper = carroMapper;
    }

    public Page<CarroEntity> buscarTodos(Pageable pageable) {
        return carroRepository.findAll(pageable);
    }

    public CarroEntity buscarPorId(Long id) {
        return carroRepository.findById(id).orElseThrow(() -> new CarNotFoundException("ID: " + id + " não encontrado, carro não existe"));
    }

    //Passando CreateCarroDTO nos parametros pois a funcao precisa receber os novos dados para poder criar um novo Carro
    public CarroEntity salvarCarro(CreateCarroDTO dto) {
        if (dto.marca() == null || dto.marca().isBlank()
                || dto.modelo() == null || dto.modelo().isBlank()
                || dto.ano() == null || dto.ano() <= 1960
                || dto.novo() == null
        ) {
            throw new InvalidCarDataException("Os campos estão inválidos");
        }

        //Cria se os dados estiverem valídos
        CarroEntity carro = carroMapper.toCreate(dto);
        return carroRepository.save(carro);
    }

    public CarroEntity editarCarro(Long id, UpdateCarroDTO carroAtualizado) {
        CarroEntity carro = carroRepository.findById(id)
                .orElseThrow(() -> new CarNotFoundException("ID: " + id + " não encontrado, carro não existe"));

        if (carroAtualizado.getModelo() == null || carroAtualizado.getModelo().isBlank()
                || carroAtualizado.getMarca() == null || carroAtualizado.getMarca().isBlank()
                || carroAtualizado.getAno() == null || carroAtualizado.getAno() <= 1960
                || carroAtualizado.getNovo() == null
        ) {
            throw new InvalidCarDataException("Os campos estão inválidos");
        }

        //Só cria depois de validar todos os atributos
        carroMapper.update(carroAtualizado);

        return carroRepository.save(carro);
    }

    public void excluirCarro(Long id) {
        CarroEntity carro = carroRepository.findById(id)
                .orElseThrow(() -> new CarNotFoundException("ID: " + id + " não encontrado, carro não existe"));
        carroRepository.delete(carro);
    }

}
