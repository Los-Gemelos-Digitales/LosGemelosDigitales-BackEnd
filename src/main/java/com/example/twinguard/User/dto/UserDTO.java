package com.example.twinguard.User.dto;

import lombok.Data;

@Data
public class UserDTO {
    private String username;
    private String password;
    private String email;
    private String role; // Teacher o Admin
    private String address; // Campo opcional
    private String phone;   // Campo opcional
}
