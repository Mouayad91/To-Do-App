package com.java_app.app.utilities;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class PasswordEncoderImpl {
    

    public static void main(String[] args) {
        

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        System.out.println("---------------------------------------------");

        System.out.println(passwordEncoder.encode("mody"));
      
        System.out.println("---------------------------------------------");
       
        System.out.println(passwordEncoder.encode("admin"));
      
        System.out.println("---------------------------------------------");

    }
}
