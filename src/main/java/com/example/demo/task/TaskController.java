package com.example.demo.task;

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
import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;





@RestController
@RequestMapping("/api/tasks")
public class TaskController {

	@Autowired
	private TaskService taskService;





	@GetMapping
	public List<TaskDto> getAllTask() {
		return taskService.getAllTasks();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Task> getTaskById(@PathVariable Long id) {
		Task task = taskService.getTaskById(id);
		if (task != null) {
			return ResponseEntity.ok(task);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@PostMapping
	public ResponseEntity<Task> createdTask(@RequestBody TaskRequestBody taskRequest) {
		if (taskRequest.getDateTimeStart() == null) {
			return ResponseEntity.badRequest().body(null);
		}

		Task createdTask = taskService.createTask(taskRequest);
		return ResponseEntity.ok(createdTask);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Task> updateTask(@PathVariable Long id, @RequestBody TaskRequestBody taskRequestBody) {
		if (taskRequestBody.getDateTimeStart() == null) {
			return ResponseEntity.badRequest().body(null);
		}

		Task updatedTask = taskService.updateTask(id, taskRequestBody);
		if (updatedTask != null) {
			return ResponseEntity.ok(updatedTask);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
		if (taskService.deleteTask(id)) {
			return ResponseEntity.ok().build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}



	@PostMapping("/take/{taskId}")
	public ResponseEntity<?> takeTask(@PathVariable Long taskId, @RequestBody TakeTaskRequestBody reqBody) {
	    String username = reqBody.getUsername();

	    try {
	        TaskDto updatedTask;

	        if (username == null) {
	            updatedTask = taskService.updateUserUpdateToNull(taskId);
	        } else {
	            updatedTask = taskService.takeTask(taskId, username);
	        }

	        return ResponseEntity.ok(updatedTask);
	        
	    } catch (RuntimeException e) {
	        if (e.getMessage().contains("not public")) {
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
	        }
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
	    }
	}






}
