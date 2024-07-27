package com.java_app.app.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.NOT_FOUND)

public class ResourceNotFoundExcep extends RuntimeException {
    

public ResourceNotFoundExcep(String message){
    super(message);
}

}
