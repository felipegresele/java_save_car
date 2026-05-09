package com.saveCar.SaveCar.mapper;

import com.saveCar.SaveCar.dto.carro.CreateCarroDTO;
import com.saveCar.SaveCar.dto.carro.ResponseCarroDTO;
import com.saveCar.SaveCar.dto.carro.UpdateCarroDTO;
import com.saveCar.SaveCar.entity.CarroEntity;
import org.apache.coyote.Response;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CarroMapper {

    public CarroEntity toCreate(CreateCarroDTO dto) {
        return CarroEntity.builder()
                .marca(dto.marca())
                .modelo(dto.modelo())
                .ano(dto.ano())
                .novo(dto.novo())
                .build();
    }

    public CarroEntity update(UpdateCarroDTO carroDTO) {
        return CarroEntity.builder()
                .marca(carroDTO.getMarca())
                .modelo(carroDTO.getModelo())
                .ano(carroDTO.getAno())
                .novo(carroDTO.getNovo())
                .build();
    }

    //Metodo responsavel por retornar dados pelo DTO
    //Pode ser usado para retornar dados nas APIs
    public ResponseCarroDTO toDTO(CarroEntity carro) {
        //Passando atributos direto no construtor e ja retornando
        return new ResponseCarroDTO(
                carro.getId(),
                carro.getMarca(),
                carro.getModelo(),
                carro.getAno(),
                carro.isNovo()
        );
    }

    public List<ResponseCarroDTO> toDTOs(List<CarroEntity> carros) {
        return carros.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

}
