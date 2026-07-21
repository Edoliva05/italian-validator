package com.edoardo.validator;

public class InvalidCodiceFiscaleException extends RuntimeException{
    public InvalidCodiceFiscaleException(String message) {
        super(message);
    }
}
