package com.dux.pruebatecnica.demo.exceptions;

public class EquipoNotFoundException extends RuntimeException {

    private final String message;

    public EquipoNotFoundException(String message){
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
