package com.devtyagi.juitwebkiosk.exception;

public class InvalidCredentialsException extends Exception {
    @Override
    public String getMessage() {
        return "Invalid Credentials Provided. Please check your enrollment number and password.";
    }

    @Override
    public String toString() {
        return "Invalid Credentials Provided";
    }
}
