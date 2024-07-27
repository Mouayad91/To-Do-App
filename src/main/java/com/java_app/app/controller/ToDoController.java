package com.java_app.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.java_app.app.dto.ToDoDto;
import com.java_app.app.service.ToDoService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/api/todos")
public class ToDoController {

    @Autowired
    private ToDoService toDoService;

    @PostMapping
    public ResponseEntity<ToDoDto> addToDo(@RequestBody ToDoDto toDoDto) {
        ToDoDto savedToDoDto = toDoService.addToDo(toDoDto);
        return new ResponseEntity<>(savedToDoDto, HttpStatus.CREATED);
    }
}
