package com.example.taskmanagement.infra.controllers.user.DTO;

import lombok.Data;

@Data
public class UserDTO {
    private String username;
    private String email;
    private String password;
}
