package com.example.taskmanagement.infra.daos;

import com.example.taskmanagement.infra.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepositoryDAO extends JpaRepository<UserEntity, String> {
    UserEntity findByUsername(String username);

    UserEntity findByEmail(String email);
}
