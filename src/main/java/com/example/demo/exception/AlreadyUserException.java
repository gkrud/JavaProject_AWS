package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE,reason = "user is already invited")
public class AlreadyUserException extends RuntimeException{
    public AlreadyUserException(String message) {super (message);}
}
