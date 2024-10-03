package com.example.demo.task;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaskRequestBody {
	private String taskName;
	private String description;
	private String priority;
	private String dateTimeFinish;
	private String dateTimeStart;
	private String userUpdate;
	private String status;
	private boolean visibility;
	private String userCreated;
}

