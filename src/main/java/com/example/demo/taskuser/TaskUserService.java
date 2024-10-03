package com.example.demo.taskuser;

import com.example.demo.task.Task;
import com.example.demo.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TaskUserService {

    @Autowired
    private TaskUserRepository taskUserRepository;

    public List<TaskUser> getAllTasksByUser(User user) {
        return taskUserRepository.findByUser(user);
    }

    public void assignTaskToUser(Task task, User user, String role) {
        TaskUser taskUser = new TaskUser();
        taskUser.setTask(task);
        taskUser.setUser(user);
        taskUser.setRole(role);
        taskUserRepository.save(taskUser);
    }

    public TaskUser findTaskUserByTaskAndUser(Task task, User user) {
        return taskUserRepository.findByUserAndTask(user, task);
    }

   
}
