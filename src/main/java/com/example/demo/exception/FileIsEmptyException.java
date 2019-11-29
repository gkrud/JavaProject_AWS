package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST,reason = "file is empty")
public class FileIsEmptyException extends RuntimeException {
    public FileIsEmptyException(String message) {super (message);}

}
