package com.empresa.dto.exception;

    
public class InvalidDataException extends RuntimeException {

    public InvalidDataException() {
        super("Los datos proporcionados no son válidos.");
    }

    public InvalidDataException(String message) {
        super(message);
    }

    public InvalidDataException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidDataException(Throwable cause) {
        super(cause);
    }
}

