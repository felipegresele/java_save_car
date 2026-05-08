package com.saveCar.SaveCar.mapper;

import com.saveCar.SaveCar.dto.carro.ResponseCarroDTO;
import com.saveCar.SaveCar.dto.carro.UpdateCarroDTO;
import com.saveCar.SaveCar.entity.CarroEntity;
import org.springframework.stereotype.Component;

@Component
public class CarroMapper {

    public CarroEntity toEntity(ResponseCarroDTO carroDTO) {
        CarroEntity carro = new CarroEntity();
        carro.getId();
        carro.setMarca(carroDTO.marca());
        carro.setModelo(carroDTO.modelo());
        carro.setAno(carroDTO.ano());
        carro.setNovo(carroDTO.novo());

        return carro;
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

    public CarroEntity update(UpdateCarroDTO dto, CarroEntity carro) {
        carro.setMarca(dto.getMarca());
        carro.setModelo(dto.getModelo());
        carro.setAno(dto.getAno());
        carro.setNovo(dto.getNovo());
        return carro;
    }

}
