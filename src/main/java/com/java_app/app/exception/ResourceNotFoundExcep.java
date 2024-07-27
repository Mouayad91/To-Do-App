package com.java_app.app.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.NOT_FOUND)

public class ResourceNotFoundExcep extends RuntimeException {
    

/**
 * Custom exception thrown when a resource is not found.
 * This exception used to send a 404 Not Found HTTP status.
 */

public ResourceNotFoundExcep(String message){
    super(message);
}

}
