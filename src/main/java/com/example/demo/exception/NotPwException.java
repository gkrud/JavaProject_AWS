package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_EXTENDED,reason = "it is not pw")
public class NotPwException extends RuntimeException {
    public NotPwException(String message) {
        super(message);
    }
}
