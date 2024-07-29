package com.java_app.app.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.java_app.app.dto.LoginDto;
import com.java_app.app.dto.RegisterDto;
import com.java_app.app.service.AuthService;

import lombok.AllArgsConstructor;




@AllArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    

    private AuthService authService;





    // Register REST API

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterDto registerDto){


        String response = authService.regrister(registerDto);

        return  new ResponseEntity<>(response, HttpStatus.CREATED);

    }


    @PostMapping("/login")
    public ResponseEntity<String> logIn(@RequestBody LoginDto loginDto){

       String response =  authService.login(loginDto);

       return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
