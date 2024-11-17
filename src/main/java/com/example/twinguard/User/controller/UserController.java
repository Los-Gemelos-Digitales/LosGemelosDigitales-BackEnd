package com.example.twinguard.User.controller;

import com.example.twinguard.User.dto.UserDTO;
import com.example.twinguard.User.entity.User;
import com.example.twinguard.User.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/v1/users")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody UserDTO userDTO) {
        User newUser = userService.createUserFromDTO(userDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
    }

    @PutMapping("/{role}/{id}")
    public ResponseEntity<User> updateUser(@PathVariable String role, @PathVariable Long id, @RequestBody UserDTO userDTO) {
        User updatedUser = userService.updateUserFromDTO(id, role, userDTO);
        return ResponseEntity.ok(updatedUser);
    }

    @GetMapping("/{role}/{id}")
    public ResponseEntity<User> getUserById(@PathVariable String role, @PathVariable Long id) {
        Optional<User> user = userService.getUserById(id, role);
        return user.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }
}
