package com.example.taskmanagement.infra.configs;

import com.example.taskmanagement.infra.repositories.TaskInMemoryRepository;
import com.example.taskmanagement.infra.repositories.UserInMemoryRepository;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

@TestConfiguration
@Profile("in-memory")
public class InMemoryConfig {
    @Bean
    public TaskInMemoryRepository getTaskInMemoryRepository() {
        return new TaskInMemoryRepository();
    }

    @Bean
    public UserInMemoryRepository getUserInMemoryRepository() {
        return new UserInMemoryRepository();
    }
}
