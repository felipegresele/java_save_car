package com.saveCar.SaveCar.mapper;

import com.saveCar.SaveCar.dto.auth.RegisterRequestDTO;
import com.saveCar.SaveCar.dto.usuario.ResponseUsuarioDTO;
import com.saveCar.SaveCar.entity.UsuarioEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UsuarioMapper {

    public UsuarioEntity toEntity(RegisterRequestDTO dto) {
        return UsuarioEntity.builder()
                .name(dto.getUsername())
                .email(dto.getEmail())
                .userPassword(dto.getPassword())
                .build();
    }

    public ResponseUsuarioDTO toDTO(UsuarioEntity usuario) {
        return new ResponseUsuarioDTO(
                usuario.getId(),
                usuario.getUsername(),
                usuario.getEmail());
    }

    public List<ResponseUsuarioDTO> toDTOList(List<UsuarioEntity> usuarios) {
        return usuarios.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }
}


