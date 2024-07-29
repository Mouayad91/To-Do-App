package com.java_app.app.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class JwtAuthResponse {


    private String accessToken; // JWT access token
    private String tokenType = "Bearer";  // Type of the token, defaults to "Bearer"

    
}
