package com.stackroute.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMessage> trackAlreadyExists(TrackAlreadyExistsException ex){
        ErrorMessage errorMessage=new ErrorMessage(ex.getMessage(), "Something went wrong!,Can't save the track");
        return new ResponseEntity<ErrorMessage>(errorMessage, HttpStatus.ALREADY_REPORTED);
    }

    public ResponseEntity<ErrorMessage> trackNotFound(TrackNotFoundException ex) {
        ErrorMessage errorMessage=new ErrorMessage(ex.getMessage(), "Bad Request");
        return new ResponseEntity<ErrorMessage>(errorMessage, HttpStatus.NOT_FOUND);
    }
}
