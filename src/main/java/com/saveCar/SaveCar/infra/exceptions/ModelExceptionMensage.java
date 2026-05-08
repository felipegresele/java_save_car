package com.saveCar.SaveCar.infra.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ModelExceptionMensage {

    //Mensagem de erro
    private String message;
    //Status code do erro
    private int statusCode;

    public ModelExceptionMensage(String message, int statusCode) {
        this.message = message;
        this.statusCode = statusCode;
    }

}
