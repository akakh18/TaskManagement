package com.example.taskmanagement.core.users;

import com.example.taskmanagement.core.tasks.Task;
import com.example.taskmanagement.infra.controllers.user.DTO.UserDTO;
import com.example.taskmanagement.infra.entity.UserEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

public class User {
    @Getter
    @Setter
    private String username;

    @Getter
    @Setter
    private String password;

    @Getter
    @Setter
    private String email;

    @Getter
    @Setter
    private List<Task> tasks;

    private static User getUserFrom(String username, String email, String password) {
        User result = new User();
        result.setUsername(username);
        result.setEmail(email);
        result.setPassword(password);
        return result;
    }

    public static User from(UserEntity user) {
        User result = getUserFrom(user.getUsername(), user.getEmail(), user.getPassword());
        result.setTasks(user.getTasks().stream().map(Task::from).collect(Collectors.toList()));
        return result;
    }

    public static User from(UserDTO user) {
        return getUserFrom(user.getUsername(), user.getEmail(), user.getPassword());
    }
}
