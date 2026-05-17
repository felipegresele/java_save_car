package com.saveCar.SaveCar.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
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
    @NotNull
    private int ano;

    private boolean novo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id") //Chave Estrangeira
    private UsuarioEntity usuario;

    public CarroEntity() {

    }

    public CarroEntity(Long id, String marca, String modelo, int ano, boolean novo, UsuarioEntity usuario) {
        this.id = id;
        this.marca = marca;
        this.modelo = modelo;
        this.ano = ano;
        this.novo = novo;
        this.usuario = usuario;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public UsuarioEntity getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioEntity usuario) {
        this.usuario = usuario;
    }

}
