package com.java_app.app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.java_app.app.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);
    
    boolean existsByEmail(String email);

    Optional<User> findByUsernameOrEmail(String username, String email);
}
