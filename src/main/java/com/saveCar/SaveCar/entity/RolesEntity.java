package com.saveCar.SaveCar.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;

@Entity
//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
@Builder
public class RolesEntity implements GrantedAuthority {

    //O RolesEntity vai ser uma entidade do banco
    //Vai ficar responsavel por trazer qual é o papel do usuario
    // Ao fazer login gera o token JWT e dar as permissoes nos endpoints da aplicação
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;

    public RolesEntity() {

    }

    public RolesEntity(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    //Vai pegar a permissão pelo campo name que usuário tiver
    @Override
    public @Nullable String getAuthority() {
        return name;
    }

    @Override
    public String toString() {
        return "Usuário ID: " + id + " - Papel: " + name;
    }
}
