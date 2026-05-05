package com.saveCar.SaveCar.entity.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateCarroDTO {

    private String marca;
    private String modelo;
    private int ano;
    private boolean novo;

    UpdateCarroDTO() {

    }

    UpdateCarroDTO(String marca, String modelo, int ano, boolean novo) {
        this.marca = marca;
        this.modelo = modelo;
        this.ano = ano;
        this.novo = novo;
    }

}
