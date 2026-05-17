package com.saveCar.SaveCar.infra.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(CarNotFoundException.class)
    private ResponseEntity<ModelExceptionMensage> CarNotFoundException(CarNotFoundException e) {
        ModelExceptionMensage message = new ModelExceptionMensage
                (e.getMessage(), 404);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
    }

    @ExceptionHandler(InvalidCarDataException.class)
    private ResponseEntity<ModelExceptionMensage> InvalidCarDataException(InvalidCarDataException e) {
        ModelExceptionMensage message = new ModelExceptionMensage
                (e.getMessage(), 400);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
    }

    @ExceptionHandler(InvalidTokenAcessExpection.class)
    private ResponseEntity<ModelExceptionMensage> InvalidTokenAcessException(InvalidTokenAcessExpection e) {
        ModelExceptionMensage message = new ModelExceptionMensage("Token de acesso expirado!", 401);
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(message);
    }

    @ExceptionHandler(BadRequestException.class)
    private ResponseEntity<ModelExceptionMensage> BadRequestException(BadRequestException e) {
        ModelExceptionMensage message = new ModelExceptionMensage(e.getMessage(), 500);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(message);
    }

}
