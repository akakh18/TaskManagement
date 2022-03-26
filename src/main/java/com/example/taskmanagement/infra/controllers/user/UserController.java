package com.example.taskmanagement.infra.controllers.user;

import com.example.taskmanagement.core.services.UserService;
import com.example.taskmanagement.core.tasks.Task;
import com.example.taskmanagement.core.users.User;
import com.example.taskmanagement.infra.controllers.task.dto.FilterTasksRequestDTO;
import com.example.taskmanagement.infra.controllers.task.dto.FilterTasksResponseDTO;
import com.example.taskmanagement.infra.controllers.user.DTO.AllUsersResponseDTO;
import com.example.taskmanagement.infra.controllers.user.DTO.IsAuthenticatedResponseDTO;
import com.example.taskmanagement.infra.controllers.user.DTO.UserDTO;
import com.example.taskmanagement.infra.controllers.user.DTO.UserResponseDTO;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public UserController(UserService userService, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping
    public void registerUser(@RequestBody UserDTO userParams) {
        userParams.setPassword(passwordEncoder.encode(userParams.getPassword()));
        userService.registerUser(User.from(userParams));
    }

    @PostMapping("/sessions")
    public void createSession(@RequestBody UserDTO sessionRequest) {
        System.out.println(sessionRequest);
        System.out.println(passwordEncoder.encode("expected: " + sessionRequest.getPassword()));
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(sessionRequest.getEmail(),
                        sessionRequest.getPassword())
        );

        SecurityContextHolder.clearContext();
        SecurityContext context = SecurityContextHolder.createEmptyContext();
        context.setAuthentication(authentication);
        SecurityContextHolder.setContext(context);
    }

    @DeleteMapping("/sessions")
    public void deleteSession() {
        SecurityContextHolder.clearContext();
    }

    @GetMapping("/authenticated")
    public IsAuthenticatedResponseDTO isAuthenticated(HttpServletRequest request, Principal principal) {
        System.out.println(principal);
        return new IsAuthenticatedResponseDTO(principal != null);
    }

    @PostMapping("/assigned")
    public FilterTasksResponseDTO assignedToMe(@RequestBody FilterTasksRequestDTO requestDTO) {
        List<Task> result = userService.getTasksForUser(requestDTO.getAssignees().get(0));
        return new FilterTasksResponseDTO(result);
    }

    @GetMapping
    public AllUsersResponseDTO getAllUsers() {
        return new AllUsersResponseDTO(userService.getAllUsers()
                .stream().map(user -> new UserResponseDTO(user.getUsername())).collect(Collectors.toList()));
    }

}
