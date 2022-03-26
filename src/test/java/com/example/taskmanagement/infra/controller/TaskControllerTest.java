package com.example.taskmanagement.infra.controller;

import com.example.taskmanagement.infra.configs.InMemoryConfig;
import com.example.taskmanagement.infra.configs.service.ServiceConfig;
import com.example.taskmanagement.infra.controllers.task.TaskController;
import com.example.taskmanagement.infra.controllers.task.dto.CreateTaskDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@WebMvcTest
@ContextConfiguration(classes = {
        TaskController.class,
        InMemoryConfig.class,
        ServiceConfig.class
})
@ActiveProfiles("in-memory")
public class TaskControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testTaskShouldBeCreated() throws Exception {
        CreateTaskDTO dto = new CreateTaskDTO();
        dto.setTitle("Test");
        dto.setAssignees(new String[0]);
        dto.setAuthor("Test");

        mockMvc.perform(MockMvcRequestBuilders.post("/tasks")
                .contentType(MediaType.APPLICATION_JSON)
                .content((new ObjectMapper()).writeValueAsString(dto))).andReturn();
    }

}
