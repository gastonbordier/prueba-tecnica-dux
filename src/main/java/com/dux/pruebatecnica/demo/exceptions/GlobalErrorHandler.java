package com.dux.pruebatecnica.demo.exceptions;

import com.dux.pruebatecnica.demo.controllers.EquipoController;
import com.dux.pruebatecnica.demo.dtos.ErrorResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@Slf4j
@RestControllerAdvice(basePackageClasses = {EquipoController.class})
public class GlobalErrorHandler {

    @ExceptionHandler(EquipoNotFoundException.class)
    public ResponseEntity<Object> handleCustomEntityNotFoundException(EquipoNotFoundException ex) {
        log.error(ex.getMessage(), ex);
        return new ResponseEntity<>(ErrorResponseDTO.builder()
                .mensaje(ex.getMessage())
                .codigo(HttpStatus.NOT_FOUND.value())
                .build(),
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ValidationFailedException.class)
    public ResponseEntity<Object> handleValidationFailedException(ValidationFailedException ex) {
//        log.error(ex.getMessage(), ex);
        return new ResponseEntity<>(ErrorResponseDTO.builder()
                .mensaje("La solicitud es invalida")
                .codigo(HttpStatus.BAD_REQUEST.value())
                .build(),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<Object> handleNoResourceFoundException(NoResourceFoundException ex) {
        log.error(ex.getMessage(), ex);
        return new ResponseEntity<>(ErrorResponseDTO.builder()
                .mensaje("El endpoint solicitado no existe")
                .codigo(HttpStatus.NOT_FOUND.value())
                .build(),
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Object> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        log.error(ex.getMessage(), ex);
        return new ResponseEntity<>(ErrorResponseDTO.builder()
                .mensaje("El cuerpo de la solicitud no existe o no esta en formato json")
                .codigo(HttpStatus.BAD_REQUEST.value())
                .build(),
                HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public ResponseEntity<Object> handleHttpMediaTypeNotSupportedException(HttpMediaTypeNotSupportedException ex) {
        log.error(ex.getMessage(), ex);
        return new ResponseEntity<>(ErrorResponseDTO.builder()
                .mensaje("El cuerpo de la solicitud tiene un formato no aceptado")
                .codigo(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value())
                .build(),
                HttpStatus.UNSUPPORTED_MEDIA_TYPE);
    }







}
