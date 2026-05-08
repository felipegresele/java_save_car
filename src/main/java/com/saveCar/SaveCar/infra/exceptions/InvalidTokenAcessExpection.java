package com.saveCar.SaveCar.infra.exceptions;

public class InvalidTokenAcessExpection extends RuntimeException {
    public InvalidTokenAcessExpection(String message) {
        super(message);
    }
}
