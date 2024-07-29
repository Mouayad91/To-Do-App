package com.java_app.app.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice  // Allows handling exceptions across the whole application
public class GlobalExceptionHandler {
    

    // Handles ToDoApiException
    @ExceptionHandler(ToDoApiException.class)
   
    public ResponseEntity<ErrorDetails> handelToDoAPIException(
        ToDoApiException exception, WebRequest webRequest){


            ErrorDetails errorDetails = new ErrorDetails(
                LocalDateTime.now(),
                exception.getMessage(),
                webRequest.getDescription(false)
            );

        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }


}
