package com.nss.kofekaknado.utils.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AlreadyExistException.class)
    public ResponseEntity<?> handleAlreadyExistException(WebRequest request) {
        var details = new ErrorDetails(new Date(), "This user already exist", request.getDescription(false));
        return new ResponseEntity<>(details, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(IncorrectPhoneNumberException.class)
    public ResponseEntity<?> handleIncorrectPhoneException(WebRequest  request) {
        ErrorDetails details = new ErrorDetails(new Date(), "Phone number is incorrect", request.getDescription(false));
        return new ResponseEntity<>(details, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(WrongPasswordException.class)
    public ResponseEntity<?> handleWrongPasswordException(WebRequest  request) {
        ErrorDetails details = new ErrorDetails(new Date(), "Password is incorrect", request.getDescription(false));
        return new ResponseEntity<>(details, HttpStatus.BAD_REQUEST);
    }
}
