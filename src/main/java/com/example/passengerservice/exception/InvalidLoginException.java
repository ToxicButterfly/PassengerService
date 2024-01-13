package com.example.passengerservice.exception;

public class InvalidLoginException extends Exception{
    public InvalidLoginException(String message) {
        super(message);
    }
}
