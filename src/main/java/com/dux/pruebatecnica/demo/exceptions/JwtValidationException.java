package com.dux.pruebatecnica.demo.exceptions;

public class JwtValidationException extends RuntimeException {
    private final String message;

    public JwtValidationException(String message) {
        this.message = message;
    }

    public JwtValidationException() {
        this.message = "No autorizado";
    }


    @Override
    public String getMessage() {
        return message;
    }
}
