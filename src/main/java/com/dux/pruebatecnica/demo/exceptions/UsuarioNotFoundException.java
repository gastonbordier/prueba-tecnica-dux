package com.dux.pruebatecnica.demo.exceptions;

public class UsuarioNotFoundException extends RuntimeException {

    private final String message;

    public UsuarioNotFoundException(String message){
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
