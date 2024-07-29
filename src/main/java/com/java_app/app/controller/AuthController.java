package com.java_app.app.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.java_app.app.dto.JwtAuthResponse;
import com.java_app.app.dto.LoginDto;
import com.java_app.app.dto.RegisterDto;
import com.java_app.app.service.AuthService;

import lombok.AllArgsConstructor;




@AllArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    

    private AuthService authService;





    //To register a new user
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterDto registerDto){


        String response = authService.register(registerDto); 

        return  new ResponseEntity<>(response, HttpStatus.CREATED);

    }

    //To log in a user
    @PostMapping("/login")
    public ResponseEntity<JwtAuthResponse> logIn(@RequestBody LoginDto loginDto){

       String token =  authService.login(loginDto);  // Calls the service to authenticate a user

        JwtAuthResponse jwtAuthResponse = new JwtAuthResponse(); // Creates a new JWT response
        jwtAuthResponse.setAccessToken(token); // Sets the token in the response

       return new ResponseEntity<>(jwtAuthResponse, HttpStatus.OK); // Returns a response with status 200
    }

}
