package com.saveCar.SaveCar.infra;

public class ApiConectionErrorException extends RuntimeException {

    public ApiConectionErrorException(String message) {
        super(message);
    }

}
