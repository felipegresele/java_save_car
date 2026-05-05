package com.saveCar.SaveCar.infra;

public class CarNotFoundException extends RuntimeException{

    public CarNotFoundException(String message) {
        super(message);
    }

}
