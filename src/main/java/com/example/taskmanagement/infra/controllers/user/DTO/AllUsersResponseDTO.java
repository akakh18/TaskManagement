package com.example.taskmanagement.infra.controllers.user.DTO;

import lombok.Data;

import java.util.List;

@Data
public class AllUsersResponseDTO {
    private final List<UserResponseDTO> users;

    public AllUsersResponseDTO(List<UserResponseDTO> users) {
        this.users = users;
    }
}
