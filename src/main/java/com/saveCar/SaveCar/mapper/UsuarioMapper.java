package com.saveCar.SaveCar.mapper;

import com.saveCar.SaveCar.dto.usuario.ResponseUsuarioDTO;
import com.saveCar.SaveCar.dto.usuario.UpdateUsuarioDTO;
import com.saveCar.SaveCar.entity.UsuarioEntity;

public class UsuarioMapper {

    public UsuarioEntity toEntity(ResponseUsuarioDTO dto) {
        return new UsuarioEntity()
                .builder()
                .id(dto.id())
                .name(dto.username())
                .email(dto.email())
                .userPassword(dto.password())
                .build();
    }

    public UsuarioEntity update(UpdateUsuarioDTO dto) {
        return new UsuarioEntity().builder()
                .
    }
}
