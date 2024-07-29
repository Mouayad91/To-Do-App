package com.java_app.app.security;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;

@AllArgsConstructor // Generates a constructor with required arguments
@Component // Indicates that this class is a Spring component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtTokenProvider jwtTokenProvider; // Injects JwtTokenProvider
    private final UserDetailsService userDetailsService; // Injects UserDetailsService

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String token = getTokenFromRequest(request); // Retrieves JWT from the request

        if (StringUtils.hasText(token) && jwtTokenProvider.validateToken(token)) { // Validates the token
            String username = jwtTokenProvider.getUsername(token); // Gets the username from the token

            UserDetails userDetails = userDetailsService.loadUserByUsername(username); // Loads user details

            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                userDetails, null, userDetails.getAuthorities()); // Creates an authentication token

            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request)); // Sets authentication details

            SecurityContextHolder.getContext().setAuthentication(authenticationToken); // Sets authentication in the security context
        }

        filterChain.doFilter(request, response); // Continues the filter chain
    }

    private String getTokenFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization"); // Retrieves the Authorization header
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) { // Checks if the token is present and starts with "Bearer"
            return bearerToken.substring(7); // Returns the token without the "Bearer " prefix
        }
        return null; // Returns null if the token is not present
    }
}
