package com.example.taskmanagement.infra.daos.jpa.repositories;

import com.example.taskmanagement.core.repositories.UserRepository;
import com.example.taskmanagement.core.tasks.Task;
import com.example.taskmanagement.core.users.User;
import com.example.taskmanagement.infra.daos.UserRepositoryDAO;
import com.example.taskmanagement.infra.entity.UserEntity;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
@Profile("main")
public class UserJPARepository implements UserRepository {
    private final UserRepositoryDAO userRepositoryDAO;

    public UserJPARepository(UserRepositoryDAO userRepositoryDAO) {
        this.userRepositoryDAO = userRepositoryDAO;
    }

    @Override
    public void registerUser(User registerUserRequest) {
        UserEntity newUser = new UserEntity();
        newUser.setEmail(registerUserRequest.getEmail());
        newUser.setUsername(registerUserRequest.getUsername());
        newUser.setPassword(registerUserRequest.getPassword());
        userRepositoryDAO.save(newUser);
    }

    @Override
    public List<Task> getTasksForUser(String username) {
        return getUserForUsername(username).getTasks();
    }

    @Override
    public User getUserForUsername(String username) {
        return User.from(userRepositoryDAO.findByUsername(username));
    }

    @Override
    public List<User> getAllUsers() {
        return userRepositoryDAO.findAll().stream().map(User::from).collect(Collectors.toList());
    }

    @Override
    public User getByEmail(String email) {
        return User.from(userRepositoryDAO.findByEmail(email));
    }
}
