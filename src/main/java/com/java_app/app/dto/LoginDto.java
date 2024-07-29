package com.java_app.app.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoginDto {


    private String usernameOrEmail; // Username or email for login
    private String password;  // Password for login
    
}
