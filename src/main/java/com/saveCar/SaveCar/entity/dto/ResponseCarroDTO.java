package com.saveCar.SaveCar.entity.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

public record ResponseCarroDTO(String marca, String modelo, Integer ano, Boolean novo) {
}
