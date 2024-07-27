package com.java_app.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.java_app.app.entity.ToDo;


@Repository
public interface ToDoRepository extends JpaRepository<ToDo, Long>{
    

}
