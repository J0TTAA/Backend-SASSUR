package com.sassur.backend.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice  // Anotación que indica que es un manejador global de excepciones
public class GlobalExceptionHandler {

    // Manejador para manejar excepciones genéricas
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e) {
        // Devuelve una respuesta con un mensaje de error y el código de estado 500 (Internal Server Error)
        return new ResponseEntity<>("Error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // Puedes agregar más manejadores para excepciones específicas, por ejemplo:
    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<String> handleNullPointerException(NullPointerException e) {
        return new ResponseEntity<>("NullPointerException: " + e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException e) {
        return new ResponseEntity<>("Invalid Argument: " + e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    // También podrías crear un manejador para validaciones o excepciones personalizadas
}
