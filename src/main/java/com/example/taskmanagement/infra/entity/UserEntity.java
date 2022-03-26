package com.example.taskmanagement.infra.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "users")
@Data
public class UserEntity {
    @Id
    private String username;

    private String password;

    private String email;

    @ManyToMany(mappedBy = "assignees")
    private List<TaskEntity> tasks;
}
