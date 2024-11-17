package com.example.twinguard.User.service;

import com.example.twinguard.User.dto.UserDTO;
import com.example.twinguard.User.entity.Admin;
import com.example.twinguard.User.entity.Teacher;
import com.example.twinguard.User.repository.AdminRepository;
import com.example.twinguard.User.repository.TeacherRepository;
import org.springframework.stereotype.Service;
import com.example.twinguard.User.entity.User;
import com.example.twinguard.User.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private AdminRepository adminRepository;

    // Crear un usuario basado en su rol
    public User createUserFromDTO(UserDTO userDTO) {
        if ("Teacher".equalsIgnoreCase(userDTO.getRole())) {
            Teacher teacher = new Teacher();
            populateCommonFields(userDTO, teacher);
            teacher.setAddress(userDTO.getAddress());
            teacher.setPhone(userDTO.getPhone());
            return teacherRepository.save(teacher);
        } else if ("Admin".equalsIgnoreCase(userDTO.getRole())) {
            Admin admin = new Admin();
            populateCommonFields(userDTO, admin);
            admin.setAddress(userDTO.getAddress());
            admin.setPhone(userDTO.getPhone());
            return adminRepository.save(admin);
        }
        throw new IllegalArgumentException("Invalid role provided");
    }

    // Obtener un usuario por su ID
    public Optional<User> getUserById(Long id, String role) {
        if ("Teacher".equalsIgnoreCase(role)) {
            // Transformar Optional<Teacher> a Optional<User>
            return teacherRepository.findById(id).map(teacher -> (User) teacher);
        } else if ("Admin".equalsIgnoreCase(role)) {
            // Transformar Optional<Admin> a Optional<User>
            return adminRepository.findById(id).map(admin -> (User) admin);
        }
        return Optional.empty();
    }
    // Actualizar un usuario existente
    public User updateUserFromDTO(Long id, String role, UserDTO userDTO) {
        if ("Teacher".equalsIgnoreCase(role)) {
            Optional<Teacher> optionalTeacher = teacherRepository.findById(id);
            if (optionalTeacher.isPresent()) {
                Teacher teacher = optionalTeacher.get();
                populateCommonFields(userDTO, teacher);
                teacher.setAddress(userDTO.getAddress());
                teacher.setPhone(userDTO.getPhone());
                return teacherRepository.save(teacher);
            }
        } else if ("Admin".equalsIgnoreCase(role)) {
            Optional<Admin> optionalAdmin = adminRepository.findById(id);
            if (optionalAdmin.isPresent()) {
                Admin admin = optionalAdmin.get();
                populateCommonFields(userDTO, admin);
                admin.setAddress(userDTO.getAddress());
                admin.setPhone(userDTO.getPhone());
                return adminRepository.save(admin);
            }
        }
        throw new IllegalArgumentException("User not found or invalid role");
    }

    public List<User> getAllUsers() {
        List<User> allUsers = new ArrayList<>();
        allUsers.addAll(teacherRepository.findAll());
        allUsers.addAll(adminRepository.findAll());
        return allUsers;
    }
    private void populateCommonFields(UserDTO userDTO, User user) {
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
        user.setEmail(userDTO.getEmail());
        user.setRole(userDTO.getRole());
    }
}
