package com.dux.pruebatecnica.demo.exceptions;

public class LoginException extends RuntimeException {
    private final String message;

    public LoginException(String message) {
        this.message = message;
    }

    public LoginException() {
        this.message = "La autenticacion fallo";
    }


    @Override
    public String getMessage() {
        return message;
    }
}
