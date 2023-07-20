package com.templateproject.api.controller;


import com.fasterxml.jackson.annotation.JsonView;
import com.templateproject.api.entity.User;
import com.templateproject.api.service.UserService;
import com.templateproject.api.views.Views;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

import java.util.List;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(
            UserService userService
    ) {
        this.userService = userService;
    }

    @Operation(summary = "Find users", description = "Find all users")
    @SecurityRequirement(name = "Bearer Authentication")
    @GetMapping("/users/")
    public List<User> getUsers() {
        return userService.getUsers();
    }

    @Operation(summary = "Find user", description = "Find a user")
    @SecurityRequirement(name = "Bearer Authentication")
    @GetMapping("/users/{id}")
    public User getUser(@PathVariable Long id) {
        return userService.getUser(id);
    }

    @PostMapping("/auth/register")
    public User register(@RequestBody User user) {
        return userService.register(user.getPassword(), user.getEmail(), user.getFirstname());
    }

    @PostMapping("/auth/login")
    public String login(@RequestBody User user) {
        System.out.println(user.getEmail());
        return userService.login(user.getEmail(), user.getPassword());
    }
}