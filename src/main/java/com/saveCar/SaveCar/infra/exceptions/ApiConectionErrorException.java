package com.saveCar.SaveCar.infra.exceptions;

public class ApiConectionErrorException extends RuntimeException {

    public ApiConectionErrorException(String message) {
        super(message);
    }

}
