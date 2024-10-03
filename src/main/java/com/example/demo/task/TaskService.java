package com.example.demo.task;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.demo.user.User;
import com.example.demo.user.UserRepository;

@Service
public class TaskService {
	
	@Autowired
	private TaskRepository taskRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	
	
	// Method to convert Task to TaskDto
	private TaskDto convertToDto(Task task) {
	    TaskDto dto = new TaskDto();
	    dto.setTaskId(task.getTaskId());
	    dto.setTaskName(task.getTaskName());
	    dto.setDateTimeStart(task.getDateTimeStart());
	    dto.setDescription(task.getDescription());
	    dto.setDateTimeFinish(task.getDateTimeFinish());
	    dto.setPriority(task.getPriority());    
	    dto.setVisibility(task.getVisibility());
	    dto.setStatus(task.getStatus());
	    dto.setCreatedAt(task.getCreatedAt());

	    dto.setUserCreated(task.getUserCreated() != null ? task.getUserCreated().getUsername() : null);
	    dto.setUserUpdated(task.getUserUpdate() != null ? task.getUserUpdate().getUsername() : null);

	    return dto;
	}


    //Method to convert RequestBody to Task
    private Task convertToTask(TaskRequestBody requestBody) {
    	Task task = new Task();
    	task.setTaskName(requestBody.getTaskName());
    	task.setPriority(requestBody.getPriority());
    	task.setStatus(requestBody.getStatus());
    	task.setDescription(requestBody.getDescription());
    	LocalDateTime dateTimeStart = TaskConverter.convertToLocalDateTime(requestBody.getDateTimeStart());
        LocalDateTime dateTimeFinish = TaskConverter.convertToLocalDateTime(requestBody.getDateTimeFinish());

        task.setDateTimeStart(dateTimeStart);
        task.setDateTimeFinish(dateTimeFinish);
        task.setVisibility(requestBody.isVisibility());
        	
        
        Optional<User> foundUser = userRepository.findByUsername(requestBody.getUserCreated());
    	if(foundUser == null) {
    		task.setUserCreated(null);
    	} else {
    		if(requestBody.isVisibility() == true){
    			task.setUserCreated(foundUser.get());
    		} else {
    			task.setUserCreated(foundUser.get());
    			task.setUserUpdate(foundUser.get());
    		}
    	}
    	return task;
    }
	
    public List<TaskDto> getAllTasks() {
        List<Task> tasks = taskRepository.findAll(Sort.by(Sort.Order.asc("taskId"))); // Ensure sorting by taskId
        return tasks.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }
	
	public Task createTask(TaskRequestBody task) {
		Task newTask = convertToTask(task);
		return taskRepository.save(newTask);
	}
	
	public Task getTaskById (Long id) {
		return taskRepository.findById(id).orElse(null);
	}
	
	// The updateTask function performs the necessary steps to update a task's information
	public Task updateTask(Long id, TaskRequestBody taskRequestBody) {
	    return taskRepository.findById(id).map(task -> {
	        task.setTaskName(taskRequestBody.getTaskName());
	        task.setDescription(taskRequestBody.getDescription());
	        task.setPriority(taskRequestBody.getPriority());

	        LocalDateTime dateTimeStart = TaskConverter.convertToLocalDateTime(taskRequestBody.getDateTimeStart());
	        LocalDateTime dateTimeFinish = TaskConverter.convertToLocalDateTime(taskRequestBody.getDateTimeFinish());
	        task.setDateTimeStart(dateTimeStart);
	        task.setDateTimeFinish(dateTimeFinish);

	        if (taskRequestBody.getUserUpdate() != null) {
	            Optional<User> foundUserUpdate = userRepository.findByUsername(taskRequestBody.getUserUpdate());
	            if (foundUserUpdate.isPresent()) {
	                task.setUserUpdate(foundUserUpdate.get());
	            } else {
	                task.setUserUpdate(null); 
	            }
	        }

	        if (taskRequestBody.getUserCreated() != null) {
	            Optional<User> foundUserCreated = userRepository.findByUsername(taskRequestBody.getUserCreated());
	            if (foundUserCreated.isPresent()) {
	                task.setUserCreated(foundUserCreated.get());
	            }
	        }

	        task.setVisibility(taskRequestBody.isVisibility());
	        task.setStatus(taskRequestBody.getStatus());

	        return taskRepository.save(task);
	    }).orElse(null);
	}

	
	
	public boolean deleteTask(Long id) {
		if(taskRepository.existsById(id)) {
			taskRepository.deleteById(id);
			return true;
		}
		return false;
	}
	
    public Optional<Task> findById(Long id) {
        return taskRepository.findById(id);
    }
    
    // This function allows a user to "take" a public task to perform.
    public TaskDto takeTask(Long taskId, String username) {
        Task publicTask = getTaskById(taskId);

        if (!publicTask.getVisibility()) {
            throw new RuntimeException("Task is not public and cannot be taken.");
        }

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if(publicTask.getUserUpdate() == null) {
        	publicTask.setUserUpdate(user);
        }

        Task savedTask = taskRepository.save(publicTask);

        return convertToDto(savedTask);
    }
    
    // This function is used to set the userUpdate of a task to null, which can be used when the user is no longer performing the task.
    public TaskDto updateUserUpdateToNull(Long taskId) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Task not found"));
        
        task.setUserUpdate(null);
        
        taskRepository.save(task);
        
        return convertToDto(task);
    }



}
