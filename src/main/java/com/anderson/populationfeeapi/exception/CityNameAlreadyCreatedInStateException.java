package com.anderson.populationfeeapi.exception;

public class CityNameAlreadyCreatedInStateException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public CityNameAlreadyCreatedInStateException(String message) {
        super(message);
    }

}
