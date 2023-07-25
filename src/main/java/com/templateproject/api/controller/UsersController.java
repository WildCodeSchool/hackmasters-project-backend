package com.templateproject.api.controller;

import com.templateproject.api.entity.Users;
import com.templateproject.api.service.UsersService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class UsersController {

    private final UsersService usersService;

    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    @GetMapping("/users")
    public List<Users> getUsers() {
        return usersService.getUsers();
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<Users> getUsersById(@PathVariable Long id) {
        Users users = usersService.getUser(id);
        if (users != null) {
            return ResponseEntity.ok(users);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping("/users/register")
    public ResponseEntity<String> registerUser(@RequestBody Users users) {
        // Vérifier si l'email existe déjà dans la base de données
        Users existingUser = usersService.findByEmail(users.getEmail());
        if (existingUser != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Email already exists");
        }

        // Use the firstname in the registration process
        Users newUser = usersService.register(users.getEmail(), users.getPassword(), users.getFirstname());
        return ResponseEntity.ok("User registered successfully");
    }

    @PostMapping("/auth/reset-password")
    public ResponseEntity<String> resetPassword(@RequestBody Map<String, String> request) {
        String email = request.get("email");
        usersService.sendPasswordResetEmail(email);
        return ResponseEntity.ok("Password reset email sent successfully!");
    }
    @PostMapping("/auth/login")
    public ResponseEntity<?> login(@RequestBody Users users) {
        System.out.println(users.getEmail());
        String authToken = usersService.login(users.getEmail(), users.getPassword());

        Optional<Users> loggedInUsersOptional = Optional.ofNullable(usersService.findByEmail(users.getEmail()));

        if (loggedInUsersOptional.isPresent()) {
            Users loggedInUsers = loggedInUsersOptional.get();
            Map<String, Object> response = new HashMap<>();
            response.put("authToken", authToken);
            response.put("firstname", loggedInUsers.getFirstname());

            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
        }
    }
}
