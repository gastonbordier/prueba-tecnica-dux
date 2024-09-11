package com.dux.pruebatecnica.demo.exceptions;

import jakarta.validation.ConstraintViolation;
import lombok.extern.slf4j.Slf4j;

import java.util.Set;

@Slf4j
public class ValidationFailedException extends RuntimeException {

    private final String message;

    public ValidationFailedException(Set<ConstraintViolation<Object>> constraintViolations) {
        this.message = "Ocurrieron errores en la validacion";
        for (ConstraintViolation<Object> violation : constraintViolations) {
            log.error(violation.getMessage());
        }
    }

    @Override
    public String getMessage() {
        return message;
    }
}
