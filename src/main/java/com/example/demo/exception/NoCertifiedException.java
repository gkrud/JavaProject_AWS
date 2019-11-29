package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE,reason = "no Certified")
public class NoCertifiedException extends RuntimeException{
    public NoCertifiedException(String message) {
        super(message);
    }
}
