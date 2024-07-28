package com.java_app.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {


  // password encoder
  @Bean
  public static PasswordEncoder passwordEncoder(){



     return new BCryptPasswordEncoder();

  }
  






  @Bean
  SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
      http.csrf().disable()
          .authorizeHttpRequests((authorize) -> {
              authorize
                  .requestMatchers(HttpMethod.POST, "/api/**").hasRole("ADMIN")
                  .requestMatchers(HttpMethod.PUT, "/api/**").hasRole("ADMIN")
                  .requestMatchers(HttpMethod.DELETE, "/api/**").hasRole("ADMIN")
                  .anyRequest().authenticated();
          })
                .httpBasic(Customizer.withDefaults());

      return http.build();
  }

    @Bean
    public UserDetailsService userDetailsService() {
     
     
   
     
        UserDetails mody = User.builder()
            .username("mody")
            .password(passwordEncoder().encode("225323")) 
            .roles("USER")
            .build();
        
        UserDetails admin = User.builder()
            .username("admin")
            .password(passwordEncoder().encode("admin")) 
            .roles("ADMIN")
            .build();

        return new InMemoryUserDetailsManager(mody, admin);
    }
}
