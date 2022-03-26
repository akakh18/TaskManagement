package com.example.taskmanagement.core.services;

import com.example.taskmanagement.core.repositories.UserRepository;
import com.example.taskmanagement.core.tasks.Task;
import com.example.taskmanagement.core.users.User;

import java.util.List;

public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void registerUser(User userParams) {
        userParams.setPassword(userParams.getPassword());
        userRepository.registerUser(userParams);
    }

    public List<Task> getTasksForUser(String username) {
        return userRepository.getTasksForUser(username);
    }

    public User getUserByUsername(String username) {
        return userRepository.getUserForUsername(username);
    }

    public void setTaskToUser(String username, Task task) {
        userRepository.getUserForUsername(username).setTasks(List.of(task));
    }

    public List<User> getAllUsers() {
        return userRepository.getAllUsers();
    }

    public User getByEmail(String email) {
        return userRepository.getByEmail(email);
    }
}
