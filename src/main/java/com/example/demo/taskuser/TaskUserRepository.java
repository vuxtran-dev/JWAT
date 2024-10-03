package com.example.demo.taskuser;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.task.Task;
import com.example.demo.user.User;

public interface TaskUserRepository extends JpaRepository<TaskUser, Long> {
	
	TaskUser findByUserAndTask(User user, Task task);
	
	List<TaskUser> findByUser(User user);

}
