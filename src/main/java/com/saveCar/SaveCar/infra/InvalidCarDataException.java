package com.saveCar.SaveCar.infra;

public class InvalidCarDataException extends RuntimeException{

    public InvalidCarDataException(String message) {
        super(message);
    }

}
