package com.example.taskmanagement.infra.controllers.task.dto;

import lombok.Data;

@Data
public class ChangeStatusDTO {
    private final String title;
    private final String status;
}
