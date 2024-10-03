package com.example.demo.task;

import java.time.LocalDateTime;

import com.example.demo.user.User;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import com.example.demo.taskuser.TaskUser;
import java.util.List;

@Entity
@Table (name = "Task")
public class Task {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	private Long taskId;
	
	@OneToMany(mappedBy = "task", cascade = CascadeType.ALL)
    private List<TaskUser> taskUsers; 
	
	@Column(name = "status")
	private String status;
	
	@Column(name = "date_time_start", nullable = false)
	private LocalDateTime dateTimeStart;
	
	@Column(name = "description", columnDefinition = "TEXT")
	private String description;
	
	@Column (name = "date_time_finish")
	private LocalDateTime dateTimeFinish;
	
	@Column(name = "priority", length = 50)
	private String priority;
	
	@ManyToOne
	@JoinColumn(name = "user_update")
	private User userUpdate;
	
	@ManyToOne
	@JoinColumn(name = "user_created")
	private User userCreated;
	
	@Column(name = "task_name", nullable = false, length = 255)
	private String taskName;
	
	@Column(name = "created_at", nullable = false, updatable = false)
	private LocalDateTime createdAt;	
	
    @Column(name = "visibility", nullable = false)
    private boolean visibility; 
    
    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

	public boolean getVisibility() {
		return visibility;
	}

	public void setVisibility(boolean visibility) {
		this.visibility = visibility;
	}

	public Long getTaskId() {
		return taskId;
	}

	public void setTaskId(Long taskId) {
		this.taskId = taskId;
	}


	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public List<TaskUser> getTaskUsers() {
		return taskUsers;
	}

	public void setTaskUsers(List<TaskUser> taskUsers) {
		this.taskUsers = taskUsers;
	}


	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}


	public LocalDateTime getDateTimeStart() {
		return dateTimeStart;
	}

	public void setDateTimeStart(LocalDateTime dateTimeStart) {
		this.dateTimeStart = dateTimeStart;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDateTime getDateTimeFinish() {
		return dateTimeFinish;
	}

	public void setDateTimeFinish(LocalDateTime dateTimeFinish) {
		this.dateTimeFinish = dateTimeFinish;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public User getUserUpdate() {
		return userUpdate;
	}

	public void setUserUpdate(User userUpdate) {
		this.userUpdate = userUpdate;
	}

	public User getUserCreated() {
		return userCreated;
	}

	public void setUserCreated(User userCreated) {
		this.userCreated = userCreated;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
	
}
