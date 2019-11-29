package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE,reason = "User is already exists")
public class UserIsExistsException extends RuntimeException {
    public UserIsExistsException(String message) {
        super(message);
    }
}
