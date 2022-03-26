package com.example.taskmanagement.infra.controllers.user.DTO;

import lombok.Data;

@Data
public class IsAuthenticatedResponseDTO {
    private final boolean authenticated;

    public IsAuthenticatedResponseDTO(boolean authenticated) {
        this.authenticated = authenticated;
    }
}
