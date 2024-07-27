package com.java_app.app.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.java_app.app.dto.ToDoDto;
import com.java_app.app.entity.ToDo;
import com.java_app.app.repository.ToDoRepository;
import com.java_app.app.service.ToDoService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ToDoServiceImpl implements ToDoService {

    private ToDoRepository toDoRepository;

    private ModelMapper modelMapper;

    @Override
    public ToDoDto addToDo(ToDoDto toDoDto) {
       
        // Convert ToDoDto into ToDo entity
        // ToDo toDo = new ToDo();
        // toDo.setTitle(toDoDto.getTitle());
        // toDo.setDescription(toDoDto.getDescription());
        // toDo.setCompleted(toDoDto.isCompleted());

        ToDo toDo = modelMapper.map(toDoDto, ToDo.class);

        // Save ToDo entity
        ToDo savedToDo = toDoRepository.save(toDo);

        // Convert saved ToDo entity to ToDoDto
        // ToDoDto savedToDoDto = new ToDoDto();
        // savedToDoDto.setId(savedToDo.getId());
        // savedToDoDto.setTitle(savedToDo.getTitle());
        // savedToDoDto.setDescription(savedToDo.getDescription());
        // savedToDoDto.setCompleted(savedToDo.isCompleted());
        ToDoDto savedToDoDto = modelMapper.map(savedToDo, ToDoDto.class);

        return savedToDoDto;
    }
}
