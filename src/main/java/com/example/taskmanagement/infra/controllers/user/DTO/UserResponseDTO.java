package com.example.taskmanagement.infra.controllers.user.DTO;

import lombok.Data;

@Data
public class UserResponseDTO {
    private final String username;

    public UserResponseDTO(String username) {
        this.username = username;
    }
}
