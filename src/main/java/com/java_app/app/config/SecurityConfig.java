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

import lombok.AllArgsConstructor;

@Configuration
@EnableMethodSecurity
@AllArgsConstructor
public class SecurityConfig {


    private UserDetailsService userDetailsService;

  // password encoder
  @Bean
  public static PasswordEncoder passwordEncoder(){



     return new BCryptPasswordEncoder();

  }
  
  @Bean
  public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception{


    return configuration.getAuthenticationManager();


  }






  @Bean
  SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
      http.csrf().disable()
          .authorizeHttpRequests((authorize) -> {
              authorize
                //   .requestMatchers(HttpMethod.POST, "/api/**").hasRole("ADMIN")
                //   .requestMatchers(HttpMethod.PUT, "/api/**").hasRole("ADMIN")
                //   .requestMatchers(HttpMethod.DELETE, "/api/**").hasRole("ADMIN")
                //   .requestMatchers(HttpMethod.GET, "/api/**").hasAnyRole("ADMIN","USER")
                //   .requestMatchers(HttpMethod.PATCH,"/api/**").hasAnyRole("ADMIN","USER")
                //     .requestMatchers(HttpMethod.GET,"/api/**").permitAll()
                  .requestMatchers("/api/auth/**").permitAll()
                     .anyRequest().authenticated();
          })
                .httpBasic(Customizer.withDefaults());

      return http.build();
  }


    // @Bean
    // public UserDetailsService userDetailsService() {
     
     
   
     
    //     UserDetails mody = User.builder()
    //         .username("mody")
    //         .password(passwordEncoder().encode("225323")) 
    //         .roles("USER")
    //         .build();
        
    //     UserDetails admin = User.builder()
    //         .username("admin")
    //         .password(passwordEncoder().encode("admin")) 
    //         .roles("ADMIN")
    //         .build();

    //     return new InMemoryUserDetailsManager(mody, admin);
    // }
}
