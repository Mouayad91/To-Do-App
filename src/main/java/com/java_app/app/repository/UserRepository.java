package com.java_app.app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.java_app.app.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);  // Finds a user by username
    
    boolean existsByEmail(String email);  // Checks if a user with the given email exists

    Optional<User> findByUsernameOrEmail(String username, String email); // Finds a user by username or email

    boolean existsByUsername(String username);  // Checks if a user with the given username exists

}
