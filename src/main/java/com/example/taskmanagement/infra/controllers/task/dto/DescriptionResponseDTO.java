package com.example.taskmanagement.infra.controllers.task.dto;

import lombok.Data;

@Data
public class DescriptionResponseDTO {
    private final String description;

    public DescriptionResponseDTO(String description) {
        this.description = description;
    }
}
