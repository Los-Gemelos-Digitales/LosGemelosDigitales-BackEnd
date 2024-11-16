package com.example.twinguard.User.repository;

import com.example.twinguard.User.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // Método para encontrar un usuario por su nombre de usuario
    Optional<User> findByUsername(String username);

    // Método para encontrar un usuario por su correo electrónico
    Optional<User> findByEmail(String email);
}