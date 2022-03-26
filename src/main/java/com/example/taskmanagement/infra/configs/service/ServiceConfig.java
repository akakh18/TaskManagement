package com.example.taskmanagement.infra.configs.service;

import com.example.taskmanagement.core.repositories.TaskRepository;
import com.example.taskmanagement.core.repositories.UserRepository;
import com.example.taskmanagement.core.services.TaskService;
import com.example.taskmanagement.core.services.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfig {
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    public ServiceConfig(TaskRepository taskRepository, UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }

    @Bean
    public UserService getUserService() {
        return new UserService(userRepository);
    }

    @Bean
    public TaskService getTaskService() {
        return new TaskService(taskRepository, getUserService());
    }

}
