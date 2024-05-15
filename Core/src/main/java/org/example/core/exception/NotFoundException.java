package org.example.core.exception;

public class NotFoundException extends Exception {
    private final String code;

    public NotFoundException(String message, String code) {
        super(message);
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
