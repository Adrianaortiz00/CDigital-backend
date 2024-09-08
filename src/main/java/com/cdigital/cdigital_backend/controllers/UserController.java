package com.cdigital.cdigital_backend.controllers;

import com.cdigital.cdigital_backend.models.User;
import com.cdigital.cdigital_backend.security.AuthResponse;
import com.cdigital.cdigital_backend.security.LoginRequest;
import com.cdigital.cdigital_backend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<User>> getUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> registerUser(@RequestParam String name,
                                                            @RequestParam String email,
                                                            @RequestParam String password) {
        String encodedPassword = userService.encodePassword(password);
        userService.registerUser(name, email, encodedPassword);
        Map<String, String> response = new HashMap<>();
        response.put("message", "User registered successfully");
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest loginRequest) {
        AuthResponse response = userService.login(loginRequest);
        return ResponseEntity.ok(response);
    }
}
