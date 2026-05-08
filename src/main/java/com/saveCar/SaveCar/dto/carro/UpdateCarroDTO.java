package com.saveCar.SaveCar.dto.carro;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateCarroDTO {

    private String marca;
    private String modelo;
    private Integer ano;
    private Boolean novo;

    UpdateCarroDTO() {

    }

    UpdateCarroDTO(String marca, String modelo, Integer ano, Boolean novo) {
        this.marca = marca;
        this.modelo = modelo;
        this.ano = ano;
        this.novo = novo;
    }

}
