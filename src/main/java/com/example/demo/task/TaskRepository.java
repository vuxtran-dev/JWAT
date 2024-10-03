package com.example.demo.task;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    
    // Tìm tất cả các task public (visibility = true)
    List<Task> findByVisibilityTrue();

    // Tìm tất cả các task private (visibility = false) mà thuộc về user đã tạo task đó
    List<Task> findByVisibilityFalseAndUserCreated_Username(String username);
}
