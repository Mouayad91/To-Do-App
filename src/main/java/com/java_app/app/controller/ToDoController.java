package com.java_app.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.java_app.app.dto.ToDoDto;
import com.java_app.app.service.ToDoService;

import lombok.AllArgsConstructor;


/**
 * ToDoController class handles incoming HTTP requests related to ToDo operations
 * such as creating, retrieving, updating, deleting, and marking ToDos as complete or incomplete.
 */

@AllArgsConstructor
@RestController
@RequestMapping("/api/todos")
public class ToDoController {

    @Autowired //Injects the ToDoService bean into this controller to use its methods
    private ToDoService toDoService;



    /**
     * To create a new To Do.
     * @param toDoDto the To Do data transfer object containing the details of the ToDo to be created.
     * @return ResponseEntity containing the created ToDo and HTTP status.
     */

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<ToDoDto> addToDo(@RequestBody ToDoDto toDoDto) {
        ToDoDto savedToDoDto = toDoService.addToDo(toDoDto);         //Call the service to add a new ToDo
        return new ResponseEntity<>(savedToDoDto, HttpStatus.CREATED); // Return the created ToDo with HTTP status 201
    }



    /**
     * Get a ToDo by its ID.
     * @param toDoId the ID of the ToDo to be retrieved.
     * @return ResponseEntity containing the retrieved ToDo and HTTP status.
     */
    
     @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping("{id}")
    public ResponseEntity<ToDoDto> getToDoById(@PathVariable("id") Long toDoId){

       ToDoDto toDoDto = toDoService.getToDo(toDoId); // Call the service to get the ToDo by ID

       return new ResponseEntity<>(toDoDto, HttpStatus.OK); // Return the retrieved ToDo with HTTP status 200
    }


    /**
     * Get all ToDos.
     * @return ResponseEntity containing the list of all ToDos and HTTP status.
     */
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping
    public ResponseEntity<List<ToDoDto>> getAllToDos(){

    List<ToDoDto> todos = toDoService.getAllToDos(); // Call the service to get all ToDos

    return new ResponseEntity<>(todos, HttpStatus.OK); // Return the list of ToDos with HTTP status 200

 }

 
    /**
     * Update a ToDo by its ID.
     * @param toDoDto the ToDo data transfer object containing the updated details of the ToDo.
     * @param id the ID of the ToDo to be updated.
     * @return ResponseEntity containing the updated ToDo and HTTP status.
     */
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("{id}") // Maps HTTP PUT requests with a path variable to this method
    public ResponseEntity<ToDoDto> updateToDo(@RequestBody ToDoDto toDoDto, @PathVariable("id") Long id){

      ToDoDto updateToDo = toDoService.updateToDo(toDoDto, id); // Call the service to update the ToDo

       return new ResponseEntity<>(updateToDo, HttpStatus.OK);     // Return the updated ToDo with HTTP status 200 (OK)
    }


    /**
     * Delete a ToDo by its ID.
     * @param id the ID of the ToDo to be deleted.
     * @return ResponseEntity containing a success message and HTTP status.
     */

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("{id}")

    public ResponseEntity<String> deletetToDo(@PathVariable Long id){

     toDoService.deleteToDo(id); // Call the service to delete the ToDo by ID

    return ResponseEntity.ok("To Do is deleted successfully!");  // Return a success message with HTTP status 200 (OK)
   
}


    /**
     * Mark a ToDo as complete by its ID.
     * @param id the ID of the ToDo to be marked as complete.
     * @return ResponseEntity containing the updated ToDo and HTTP status.
     */
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @PatchMapping("{id}/complete")
    public ResponseEntity<ToDoDto> completeToDo(@PathVariable Long id){


      ToDoDto updatedToDo =  toDoService.completeToDo(id);

      return ResponseEntity.ok(updatedToDo);
    }


     /**
     * Mark a ToDo as incomplete by its ID.
     * @param id the ID of the ToDo to be marked as incomplete.
     * @return ResponseEntity containing the updated ToDo and HTTP status.
     */
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")  // Allows users with ADMIN or USER roles
    @PatchMapping("{id}/incomplete")
    public ResponseEntity<ToDoDto> inCompleteToDo(@PathVariable Long id){


      ToDoDto updatedToDo =  toDoService.inCompleteToDo(id);

      return ResponseEntity.ok(updatedToDo);
    }

}
