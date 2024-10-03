package com.example.demo.task;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.example.demo.user.UserDto; 

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaskDto {
	private Long taskId;
	private String taskName;
	private LocalDateTime dateTimeStart;
	private String description;
	private LocalDateTime dateTimeFinish;
	private String priority;
	private Boolean visibility;
	private String status;
	private LocalDateTime createdAt;
	private String userCreated; 
	private String userUpdated;
	
	
//	public Long getTaskId() {
//		return taskId;
//	}
//	public void setTaskId(Long taskId) {
//		this.taskId = taskId;
//	}
//	public String getTaskName() {
//		return taskName;
//	}
//	public void setTaskName(String taskName) {
//		this.taskName = taskName;
//	}
//	public LocalDateTime getDateTimeStart() {
//		return dateTimeStart;
//	}
//	public void setDateTimeStart(LocalDateTime dateTimeStart) {
//		this.dateTimeStart = dateTimeStart;
//	}
//	public String getDescription() {
//		return description;
//	}
//	public void setDescription(String description) {
//		this.description = description;
//	}
//	public LocalDateTime getDateTimeFinish() {
//		return dateTimeFinish;
//	}
//	public void setDateTimeFinish(LocalDateTime dateTimeFinish) {
//		this.dateTimeFinish = dateTimeFinish;
//	}
//	public String getPriority() {
//		return priority;
//	}
//	public void setPriority(String priority) {
//		this.priority = priority;
//	}
//	public TaskDto(Long taskId, String taskName, LocalDateTime dateTimeStart, String description,
//			LocalDateTime dateTimeFinish, String priority) {
//		super();
//		this.taskId = taskId;
//		this.taskName = taskName;
//		this.dateTimeStart = dateTimeStart;
//		this.description = description;
//		this.dateTimeFinish = dateTimeFinish;
//		this.priority = priority;
//	}
//	public TaskDto() {
//		super();
//		// TODO Auto-generated constructor stub
//	}
	
	
}
