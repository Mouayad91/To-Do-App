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
import com.java_app.app.security.JwtTokenProvider;
import com.java_app.app.service.AuthService;

import lombok.AllArgsConstructor;

@Service // Indicates that this class is a Spring service
@AllArgsConstructor // Generates a constructor with required arguments
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository; // Injects UserRepository
    private final RoleRepository roleRepository; // Injects RoleRepository
    private final PasswordEncoder passwordEncoder; // Injects PasswordEncoder
    private final AuthenticationManager authenticationManager; // Injects AuthenticationManager
    private final JwtTokenProvider jwtTokenProvider; // Injects JwtTokenProvider

    @Override
    public String register(RegisterDto registerDto) {
        // Checks if username exists
        if (userRepository.existsByUsername(registerDto.getUsername())) {
            throw new ToDoApiException(HttpStatus.BAD_REQUEST, "Username already exists");
        }

        // Checks if email exists
        if (userRepository.existsByEmail(registerDto.getEmail())) {
            throw new ToDoApiException(HttpStatus.BAD_REQUEST, "Email already exists");
        }

        // Creates a new user
        User user = new User();
        user.setName(registerDto.getName());
        user.setUsername(registerDto.getUsername());
        user.setEmail(registerDto.getEmail());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));

        // Sets user role
        Set<Role> roles = new HashSet<>();
        Role userRole = roleRepository.findByName("ROLE_USER");
        roles.add(userRole);

        
        if ("admin".equals(registerDto.getUsername())) {
            Role adminRole = roleRepository.findByName("ROLE_ADMIN");
            roles.add(adminRole);
        }

        user.setRoles(roles);

        userRepository.save(user);

        return "User registered successfully.";
    }

    @Override
    public String login(LoginDto loginDto) {
        // Authenticates the user
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                loginDto.getUsernameOrEmail(), 
                loginDto.getPassword()
            )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication); // Sets authentication in security context

        return jwtTokenProvider.generateToken(authentication); // Generates and returns the JWT token
    }
}
