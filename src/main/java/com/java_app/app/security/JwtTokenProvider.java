package com.java_app.app.security;

import java.security.Key;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component // Indicates that this class is a Spring component
public class JwtTokenProvider {

    @Value("${app.jwt-secret}") // Injects the JWT secret from application properties
    private String jwtSecret;

    @Value("${app.jwt-expiration-milliseconds}") // Injects the JWT expiration time from application properties
    private long jwtExpirationDate;

    // Generates a JWT token
    public String generateToken(Authentication authentication) {
        String username = authentication.getName(); // Retrieves the username from authentication
        Date currentDate = new Date(); // Gets the current date
        Date expireDate = new Date(currentDate.getTime() + jwtExpirationDate); // Calculates the expiration date

        return Jwts.builder()
            .setSubject(username)
            .setIssuedAt(currentDate)
            .setExpiration(expireDate)
            .signWith(key()) // Signs the token with the secret key
            .compact();
    }

    // Retrieves the key from the secret
    private Key key() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret)); // Decodes and returns the secret key
    }

    // Gets the username from the JWT token
    public String getUsername(String token) {
        Claims claims = Jwts.parserBuilder()
            .setSigningKey(key())
            .build()
            .parseClaimsJws(token) // Correct method to parse signed JWT
            .getBody();

        return claims.getSubject(); // Returns the username
    }

    // Validates the JWT token
    public boolean validateToken(String token) {
        Jwts.parserBuilder()
            .setSigningKey(key())
            .build()
            .parseClaimsJws(token); // Correct method to parse signed JWT

        return true; // Returns true if the token is valid
    }
}
