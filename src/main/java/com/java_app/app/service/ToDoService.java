package com.java_app.app.service;

import java.util.List;

import com.java_app.app.dto.ToDoDto;

public interface ToDoService {
    

    ToDoDto addToDo( ToDoDto toDoDto);

    ToDoDto getToDo (Long id);

    List<ToDoDto> getAllToDos();
}
