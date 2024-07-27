package com.java_app.app.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


/**
 * Data Transfer Object for ToDo entity.
 * This class is used to transfer ToDo data between different layers of the application.
 */



@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class ToDoDto {
    

    private Long id;
    private String title;
    private String description;
    private boolean completed;


}
