package com.example.taskmanagement.infra.controllers.task.dto;

import com.example.taskmanagement.core.tasks.Task;
import lombok.Data;

import java.util.List;

@Data
public class FilterTasksResponseDTO {
    private List<Task> tasks;

    public FilterTasksResponseDTO(List<Task> tasks) {
        this.tasks = tasks;
    }
}
