package com.java_app.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.java_app.app.entity.ToDo;

/**
 * Repository interface for ToDo entities.
 * It provides CRUD operations for the ToDo entity.
 */
@Repository  // Indicates that this interface is a Spring repository

public interface ToDoRepository extends JpaRepository<ToDo, Long>{ // Extends JpaRepository to provide CRUD operations 
    

}
