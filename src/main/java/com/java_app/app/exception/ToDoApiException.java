package com.java_app.app.exception;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
@AllArgsConstructor

public class ToDoApiException extends RuntimeException {


private HttpStatus status;
private String message;


    
}
