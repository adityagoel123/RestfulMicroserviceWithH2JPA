package com.adityaebs.task;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class TaskController {
	
	@Autowired
	private TaskForUserRepository taskForUserRepository;

	
	@GetMapping("/users/{username}/tasks")
	public List<Task> getAllTodos(@PathVariable String username){
		return taskForUserRepository.findByUsername(username);
	}

	@GetMapping("/users/{username}/tasks/{id}")
	public Task getTodo(@PathVariable String username, @PathVariable long id){
		return taskForUserRepository.findById(id).get();
	}

	@DeleteMapping("/users/{username}/tasks/{id}")
	public ResponseEntity<Void> deleteTodo(
			@PathVariable String username, @PathVariable long id) {

		taskForUserRepository.deleteById(id);

		return ResponseEntity.noContent().build();
	}
	

	@PutMapping("/users/{username}/tasks/{id}")
	public ResponseEntity<Task> updateTodo(
			@PathVariable String username,
			@PathVariable long id, @RequestBody Task task){
		
		task.setUsername(username);
		
		Task taskUpdated = taskForUserRepository.save(task);
		
		return new ResponseEntity<Task>(taskUpdated, HttpStatus.OK);
	}
	
	@PostMapping("/users/{username}/tasks")
	public ResponseEntity<Void> createTodo(
			@PathVariable String username, @RequestBody Task task){
		
		task.setId(-1L);
				
		Task createdTask = taskForUserRepository.save(task);
		
		//Location
		//Get current resource url
		///{id}
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(createdTask.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
		
}
