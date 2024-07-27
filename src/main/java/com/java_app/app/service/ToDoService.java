package com.java_app.app.service;

import java.util.List;

import com.java_app.app.dto.ToDoDto;


/**
 * Service interface for ToDo operations.
 * Defines the methods for managing ToDos.
 */

public interface ToDoService {
    

    ToDoDto addToDo( ToDoDto toDoDto); // Method to add a new ToDo

    ToDoDto getToDo (Long id); // Method to get a ToDo by its ID

    List<ToDoDto> getAllToDos(); // Method to get all ToDos

    ToDoDto updateToDo(ToDoDto toDoDto, Long id); // Method to update a ToDo by its ID

    void deleteToDo(Long id); // Method to delete a ToDo by its ID

    ToDoDto completeToDo(Long id); // Method to mark a ToDo as complete by its ID

    ToDoDto inCompleteToDo(Long id); // Method to mark a ToDo as incomplete by its ID
}
