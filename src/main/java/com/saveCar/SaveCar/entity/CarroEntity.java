package com.saveCar.SaveCar.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Entity
@Table(name = "carro")
//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
@Builder()
public class CarroEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String marca;
    @NotBlank
    private String modelo;
    @NotBlank
    private int ano;
    @NotBlank
    private boolean novo;

    public CarroEntity() {

    }

    public CarroEntity(Long id, String marca, String modelo, int ano, boolean novo) {
        this.id = id;
        this.marca = marca;
        this.modelo = modelo;
        this.ano = ano;
        this.novo = novo;
    }

    public Long getId() {
        return id;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public boolean isNovo() {
        return novo;
    }

    public void setNovo(boolean novo) {
        this.novo = novo;
    }

}
