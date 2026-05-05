package com.saveCar.SaveCar.infra;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ModelExceptionMensage {

    private String message;
    private int statusCode;

    public ModelExceptionMensage(String message, int statusCode) {
        this.message = message;
        this.statusCode = statusCode;
    }

}
