package com.example.taskmanagement.infra.configs.security;

import com.example.taskmanagement.core.services.UserService;
import com.example.taskmanagement.core.users.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class TaskManagementUserDetailsService implements UserDetailsService {

    private final UserService userService;

    public TaskManagementUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.getByEmail(username);
        System.out.println("actual: " + user.getPassword());
        return new TaskManagementUserDetails(user.getEmail(), user.getPassword());
    }
}
