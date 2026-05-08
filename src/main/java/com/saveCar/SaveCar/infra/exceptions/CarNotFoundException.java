package com.saveCar.SaveCar.infra.exceptions;

public class CarNotFoundException extends RuntimeException{

    public CarNotFoundException(String message) {
        super(message);
    }

}
