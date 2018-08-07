package com.company;

public class AppException extends Exception {
    private String message;

    AppException(String message) {
        this.message = message;
    }
    AppException() {}
    @Override
    public String getMessage() {
        return message;
    }
}
