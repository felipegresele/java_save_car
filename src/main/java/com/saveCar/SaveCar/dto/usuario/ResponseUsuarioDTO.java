package com.saveCar.SaveCar.dto.usuario;

import com.saveCar.SaveCar.entity.RolesEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

public record ResponseUsuarioDTO(Long id, String username, String email) {
}
