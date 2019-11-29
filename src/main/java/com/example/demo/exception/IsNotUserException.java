package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE,reason = "is not User")
public class IsNotUserException extends RuntimeException{
    public IsNotUserException(String message) {
        super(message);
    }
}