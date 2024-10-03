package com.example.demo.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class TaskConverter {
    // Các định dạng chuỗi ngày giờ
    private static final DateTimeFormatter[] DATE_TIME_FORMATTERS = new DateTimeFormatter[] {
            DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"),
            DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    };

    public Task convertToTask(TaskRequestBody requestBody) {
        Task task = new Task();
        task.setTaskName(requestBody.getTaskName());
        task.setPriority(requestBody.getPriority());
        task.setStatus(requestBody.getStatus());
        task.setDescription(requestBody.getDescription());
        task.setDateTimeFinish(convertToLocalDateTime(requestBody.getDateTimeFinish()));
        task.setDateTimeStart(convertToLocalDateTime(requestBody.getDateTimeStart()));
        
        return task;
    }

    public static LocalDateTime convertToLocalDateTime(String dateTimeString) {
        if (dateTimeString == null || dateTimeString.isEmpty()) {
            return null; // or handle accordingly
        }
        for (DateTimeFormatter formatter : DATE_TIME_FORMATTERS) {
            try {
                return LocalDateTime.parse(dateTimeString, formatter);
            } catch (DateTimeParseException e) {
                // Continue to the next formatter
            }
        }
        throw new DateTimeParseException("Unable to parse date time string: " + dateTimeString, dateTimeString, 0);
    }
}
