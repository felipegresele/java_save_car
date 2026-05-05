package com.saveCar.SaveCar.infra;

import org.springframework.boot.Banner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(CarNotFoundException.class)
    private ResponseEntity<ModelExceptionMensage> CarNotFoundException(CarNotFoundException e) {
        ModelExceptionMensage mensagem = new ModelExceptionMensage
                (e.getMessage(), 400);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mensagem);
    }

    @ExceptionHandler(ApiConectionErrorException.class)
    private ResponseEntity<ModelExceptionMensage> ApiConectionErrorException(ApiConectionErrorException e) {
        ModelExceptionMensage mensagem = new ModelExceptionMensage
                (e.getMessage(), 400);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mensagem);
    }

    @ExceptionHandler(InvalidCarDataException.class)
    private ResponseEntity<ModelExceptionMensage> InvalidCarDataException(InvalidCarDataException e) {
        ModelExceptionMensage mensagem = new ModelExceptionMensage
                (e.getMessage(), 400);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mensagem);
    }

}
