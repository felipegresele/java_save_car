package com.saveCar.SaveCar.entity.dto;

import com.saveCar.SaveCar.entity.Carro;
import org.springframework.stereotype.Component;

@Component
public class CarroMapper {

    public Carro toEntity(ResponseCarroDTO carroDTO) {
        Carro carro = new Carro();
        carro.setMarca(carroDTO.marca());
        carro.setModelo(carroDTO.modelo());
        carro.setAno(carroDTO.ano());
        carro.setNovo(carroDTO.novo());

        return carro;
    }

    //Metodo responsavel por retornar dados pelo DTO
    //Pode ser usado para retornar dados nas APIs
    public ResponseCarroDTO toDTO(Carro carro) {
        //Passando atributos direto no construtor e ja retornando
        return new ResponseCarroDTO(
                carro.getMarca(),
                carro.getModelo(),
                carro.getAno(),
                carro.isNovo()
        );
    }

    public Carro update(UpdateCarroDTO dto, Carro carro) {
        carro.setMarca(dto.getMarca());
        carro.setModelo(dto.getModelo());
        carro.setAno(dto.getAno());
        carro.setNovo(dto.isNovo());
        return carro;
    }

}
