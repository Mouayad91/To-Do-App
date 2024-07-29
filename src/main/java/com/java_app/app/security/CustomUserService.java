package com.java_app.app.security;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.java_app.app.entity.User;
import com.java_app.app.repository.UserRepository;

import lombok.AllArgsConstructor;


@Service
@AllArgsConstructor
public class CustomUserService implements  UserDetailsService {

    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
       
        User user = userRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail)
        .orElseThrow(()->new UsernameNotFoundException("User not exists by Username or Email"));

        Set<GrantedAuthority> authorities = user.getRoles()
        .stream()
        .map((role)-> new SimpleGrantedAuthority(role.getName())) // Maps roles to granted authorities
        .collect(Collectors.toSet());  // Collects the authorities into a set






        return new org.springframework.security.core.userdetails.User(
            usernameOrEmail,
            user.getPassword(),
            authorities
        );
    }
    
}
