package com.example.taskmanagement.infra.repositories;

import com.example.taskmanagement.core.repositories.UserRepository;
import com.example.taskmanagement.core.tasks.Task;
import com.example.taskmanagement.core.users.User;

import java.util.ArrayList;
import java.util.List;

public class UserInMemoryRepository implements UserRepository {
    private final List<User> users;

    public UserInMemoryRepository() {
        this.users = new ArrayList<>();
    }

    @Override
    public void registerUser(User registerUserRequest) {
        users.add(registerUserRequest);
    }

    @Override
    public List<Task> getTasksForUser(String username) {
        User user = getUserForUsername(username);
        return user == null ? null : user.getTasks();
    }

    @Override
    public User getUserForUsername(String username) {
        for (User user : this.users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }

        return null;
    }

    @Override
    public List<User> getAllUsers() {
        return users;
    }

    @Override
    public User getByEmail(String email) {
        for (User user : users) {
            if (user.getEmail().equals(email)) return user;
        }
        return null;
    }
}
