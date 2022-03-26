package com.example.taskmanagement.core.repositories;

import com.example.taskmanagement.core.tasks.Task;
import com.example.taskmanagement.core.users.User;

import java.util.List;

public interface UserRepository {
    void registerUser(User registerUserRequest);

    List<Task> getTasksForUser(String username);

    User getUserForUsername(String username);

    List<User> getAllUsers();

    User getByEmail(String email);
}
