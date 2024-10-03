package com.example.demo.taskuser;

import com.example.demo.task.Task;
import com.example.demo.user.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "TaskUser")
public class TaskUser {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long taskUserId;
	
	@ManyToOne
	@JoinColumn (name = "task_id")
	private Task task;
	
	@ManyToOne
	@JoinColumn (name = "user_id")
	private User user;
	
	@Column(name = "role", nullable = false)
	private String role;

	public Long getTaskUserId() {
		return taskUserId;
	}

	public void setTaskUserId(Long taskUserId) {
		this.taskUserId = taskUserId;
	}

	public Task getTask() {
		return task;
	}

	public void setTask(Task task) {
		this.task = task;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
}
