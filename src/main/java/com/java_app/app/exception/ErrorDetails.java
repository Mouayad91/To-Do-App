package com.java_app.app.exception;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ErrorDetails {


    private LocalDateTime timeStamp;  // Time of the error
    private String message;  // Error message
    private String details;  // Details of the error
    
}
