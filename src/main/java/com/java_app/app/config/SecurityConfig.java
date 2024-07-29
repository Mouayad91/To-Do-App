package com.java_app.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.java_app.app.security.JwtAuthenticationEntry;
import com.java_app.app.security.JwtAuthenticationFilter;

import lombok.AllArgsConstructor;




@Configuration  //Class has spring configs
@EnableMethodSecurity //Enable method-level security
@AllArgsConstructor



public class SecurityConfig {


    private UserDetailsService userDetailsService; // Injects UserDetailsService
    private JwtAuthenticationEntry authenticationEntry; // Injects custom authentication entry point
    private JwtAuthenticationFilter authenticationFilter; // Injects JWT authentication filter

    // Bean for password encoding using BCrypt
  @Bean
  public static PasswordEncoder passwordEncoder(){



     return new BCryptPasswordEncoder();

  }
  

  // Bean for authentication manager
  @Bean
  public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception{


    return configuration.getAuthenticationManager();


  }





  // Configures the security filter chain
  @Bean
  SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
      http.csrf().disable()                                     // Disables CSRF protection
          .authorizeHttpRequests((authorize) -> {
              authorize
                .requestMatchers("/api/auth/**").permitAll()
                .anyRequest().authenticated(); // Requires authentication for any other request
          })
                .httpBasic(Customizer.withDefaults());   // Uses basic HTTP authentication

                http.exceptionHandling(exception -> exception    
                .authenticationEntryPoint(authenticationEntry));  // Handles authentication exceptions

                http.addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class);  // Adds JWT filter

      return http.build();
  }

}
