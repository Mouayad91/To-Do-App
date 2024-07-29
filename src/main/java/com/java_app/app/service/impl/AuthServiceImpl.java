package com.java_app.app.service.impl;

import java.util.HashSet;
import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.java_app.app.dto.LoginDto;
import com.java_app.app.dto.RegisterDto;
import com.java_app.app.entity.Role;
import com.java_app.app.entity.User;
import com.java_app.app.exception.ToDoApiException;
import com.java_app.app.repository.RoleRepository;
import com.java_app.app.repository.UserRepository;
import com.java_app.app.service.AuthService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;
    private AuthenticationManager authenticationManager;

    @Override
    public String regrister(RegisterDto registerDto) {
      
        // check username if its in DB
        if(userRepository.existsByUsername(registerDto.getUsername())){

            throw new ToDoApiException(HttpStatus.BAD_REQUEST, "username is allready exists");

        }

        // check if email in DB

        if(userRepository.existsByEmail(registerDto.getEmail())){

            throw new ToDoApiException(HttpStatus.BAD_REQUEST, "Email is allready exists");
        }


        User user = new User();

        user.setName(registerDto.getName());
        user.setUsername(registerDto.getUsername());
        user.setEmail(registerDto.getEmail());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));


        Set<Role> roles = new HashSet<>();

      Role userRole =  roleRepository.findByName("ROLE_USER");
      roles.add(userRole);
      
      user.setRoles(roles);

      userRepository.save(user);

        return "User regiseterd successfully.";
    }

    @Override
    public String login(LoginDto loginDto) {
        


       Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
            loginDto.getUsernameOrEmail(), 
            loginDto.getPassword()));


        SecurityContextHolder.getContext().setAuthentication(authentication);


        return "Logged in successfully!";
    }
    




    
}
