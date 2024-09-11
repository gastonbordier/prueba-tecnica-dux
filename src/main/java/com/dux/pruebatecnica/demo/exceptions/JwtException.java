package com.dux.pruebatecnica.demo.exceptions;

public class JwtException extends RuntimeException {
    private final String message;

    public JwtException(String message) {
        this.message = message;
    }

    public JwtException() {
        this.message = "No autorizado";
    }


    @Override
    public String getMessage() {
        return message;
    }
}
