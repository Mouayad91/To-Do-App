package com.java_app.app.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.java_app.app.dto.ToDoDto;
import com.java_app.app.entity.ToDo;
import com.java_app.app.exception.ResourceNotFoundExcep;
import com.java_app.app.repository.ToDoRepository;
import com.java_app.app.service.ToDoService;

import lombok.AllArgsConstructor;

/**
 * Service implementation for ToDo operations.
 * It contains the business logic for managing ToDos.
 */

@Service // Indicates that this class is a Spring service
@AllArgsConstructor
public class ToDoServiceImpl implements ToDoService {

    private ToDoRepository toDoRepository; // Repository for CRUD operations

    private ModelMapper modelMapper; // Mapper for converting between entities and DTOs

    @Override
    public ToDoDto addToDo(ToDoDto toDoDto) {
       
        // Convert ToDoDto into ToDo entity
        // ToDo toDo = new ToDo();
        // toDo.setTitle(toDoDto.getTitle());
        // toDo.setDescription(toDoDto.getDescription());
        // toDo.setCompleted(toDoDto.isCompleted());

        ToDo toDo = modelMapper.map(toDoDto, ToDo.class); // Convert ToDoDto into ToDo entity

        // Save ToDo entity
        ToDo savedToDo = toDoRepository.save(toDo); // Save ToDo entity to the database

        // Convert saved ToDo entity to ToDoDto
        // ToDoDto savedToDoDto = new ToDoDto();
        // savedToDoDto.setId(savedToDo.getId());
        // savedToDoDto.setTitle(savedToDo.getTitle());
        // savedToDoDto.setDescription(savedToDo.getDescription());
        // savedToDoDto.setCompleted(savedToDo.isCompleted());
        ToDoDto savedToDoDto = modelMapper.map(savedToDo, ToDoDto.class); // Convert saved ToDo entity to ToDoDto and return it

        return savedToDoDto;
    }

    /**
     * Get a ToDo by its ID.
     * @param id the ID of the ToDo.
     * @return the retrieved ToDo as a data transfer object.
     */

    @Override
    public ToDoDto getToDo(Long id) {
        
      ToDo toDo = toDoRepository.findById(id)
      .orElseThrow(()-> new ResourceNotFoundExcep("To do is not found with the id of :" + id)); 

        return modelMapper.map(toDo, ToDoDto.class); // Convert ToDo entity to ToDoDto and return it
    }


    /**
     * Get all ToDos.
     * @return the list of all ToDos as data transfer objects.
     */

    @Override
    public List<ToDoDto> getAllToDos() {
       
        List<ToDo> toDos = toDoRepository.findAll(); // Retrieve all ToDos from the database
       
       
        return toDos.stream()
        .map((todo)-> modelMapper.map(todo, ToDoDto.class)) // Convert each ToDo entity to ToDoDto
        .collect(Collectors.toList()); // Collect the results into a list
    
    
    }

    /**
     * Update a ToDo by its ID.
     * @param toDoDto the ToDo data transfer object.
     * @param id the ID of the ToDo to be updated.
     * @return the updated ToDo as a data transfer object.
     */


    @Override
    public ToDoDto updateToDo(ToDoDto toDoDto, Long id) {
       

      ToDo todo =  toDoRepository.findById(id)
        .orElseThrow(()->new ResourceNotFoundExcep("To Do is not found with the id: " + id));

        todo.setTitle(toDoDto.getTitle()); // Update titl
        todo.setDescription(toDoDto.getDescription()); // Update description
        todo.setCompleted(toDoDto.isCompleted()); // Update completed status

       ToDo updateToDo = toDoRepository.save(todo); // Save updated ToDo to the database

        return modelMapper.map(updateToDo, ToDoDto.class); // Convert updated ToDo entity to ToDoDto and return it
    }


    /**
     * Delete a ToDo by its ID.
     * @param id the ID of the ToDo.
     */

    @Override
    public void deleteToDo(Long id) {
        
        ToDo todo = toDoRepository.
                    findById(id)
                    .orElseThrow(()-> new ResourceNotFoundExcep("To do not found with the id: " + id));
 
        toDoRepository.deleteById(id); // Delete the ToDo from the database

    }


    /**
     * Mark a ToDo as complete by its ID.
     * @param id the ID of the ToDo.
     * @return the updated ToDo as a data transfer object.
     */

    @Override
    public ToDoDto completeToDo(Long id) {
        
        ToDo todo = toDoRepository.findById(id)
        .orElseThrow(()-> new ResourceNotFoundExcep("To do is not found with the id: " + id));


        todo.setCompleted(Boolean.TRUE);

       ToDo updatedToDo = toDoRepository.save(todo);

        return modelMapper.map(todo, ToDoDto.class);

    }


    /**
     * Mark a ToDo as incomplete by its ID.
     * @param id the ID of the ToDo.
     * @return the updated ToDo as a data transfer object.
     */
    
    @Override
    public ToDoDto inCompleteToDo(Long id) {
         
        ToDo todo = toDoRepository.findById(id)
        .orElseThrow(()-> new ResourceNotFoundExcep("To do is not found with the id: " + id));
    
        todo.setCompleted(Boolean.FALSE);

        ToDo updatedToDo = toDoRepository.save(todo);

        return modelMapper.map(updatedToDo, ToDoDto.class);
    }
}
