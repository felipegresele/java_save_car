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
    private Long usuarioID;

    public UpdateCarroDTO() {

    }

    public UpdateCarroDTO(String marca, String modelo, Integer ano, Boolean novo, Long usuarioID) {
        this.marca = marca;
        this.modelo = modelo;
        this.ano = ano;
        this.novo = novo;
        this.usuarioID = usuarioID;
    }

}
