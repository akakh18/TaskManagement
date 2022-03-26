package com.example.taskmanagement.infra.controllers.task.dto;

import lombok.Data;

import java.util.List;

@Data
public class FilterTasksRequestDTO {
    private List<String> assignees;
    private String status;
    private boolean overdue;
}
