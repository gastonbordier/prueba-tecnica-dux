package com.dux.pruebatecnica.demo.exceptions;

import com.dux.pruebatecnica.demo.dtos.ErrorResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class GlobalErrorHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Object> handleCustomEntityNotFoundException(NotFoundException ex) {
        log.error(ex.getLocalizedMessage());
        return new ResponseEntity<>(ErrorResponseDTO.builder()
                .mensaje(ex.getMessage())
                .codigo(HttpStatus.NOT_FOUND.value())
                .build(),
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        log.error(ex.getLocalizedMessage());
        return new ResponseEntity<>(ErrorResponseDTO.builder()
                .mensaje("La solicitud es invalida")
                .codigo(HttpStatus.BAD_REQUEST.value())
                .build(),
                HttpStatus.BAD_REQUEST);
    }


}
