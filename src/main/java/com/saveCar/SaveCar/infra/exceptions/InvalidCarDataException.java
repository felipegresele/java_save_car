package com.saveCar.SaveCar.infra.exceptions;

public class InvalidCarDataException extends RuntimeException{

    public InvalidCarDataException(String message) {
        super(message);
    }

}
