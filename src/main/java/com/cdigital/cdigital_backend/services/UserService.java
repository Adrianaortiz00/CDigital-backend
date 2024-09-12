package com.cdigital.cdigital_backend.services;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.cdigital.cdigital_backend.controllers.AuthResponse;
import com.cdigital.cdigital_backend.errors.ExistingEmailError;
import com.cdigital.cdigital_backend.errors.UnauthorizedException;
import com.cdigital.cdigital_backend.errors.UserNotFoundException;
import com.cdigital.cdigital_backend.models.Role;
import com.cdigital.cdigital_backend.models.User;
import com.cdigital.cdigital_backend.repositories.RoleRepository;
import com.cdigital.cdigital_backend.repositories.UserRepository;
import com.cdigital.cdigital_backend.security.JwtUtil;

import io.jsonwebtoken.JwtException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;

    public Optional<User> getUserByID(int id) {
        return userRepository.findById(id);
    }

    public List<User> getUser() {
        return userRepository.findAll();
    }

    public User addUser(User user) {
        Optional<User> existingUser = userRepository.findByEmail(user.getEmail());
        if (existingUser.isPresent()) {
            throw new ExistingEmailError();
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public AuthResponse login(User request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("No existe un usuario con este correo electrónico"));
        authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        String token = jwtUtil.generateToken(user.getEmail());
        String userRole = user.getRole() != null ? user.getRole().getNameRol() : "ROLE_USER";

        return AuthResponse.builder()
                .userId(user.getId())
                .token(token)
                .userRole(userRole)
                .build();
    }

    public AuthResponse register(User user) {
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new ExistingEmailError();
        }

        Role userRole = roleRepository.findByNameRol("ROLE_USER")
        .orElseThrow(() -> new RuntimeException("Role not found"));

        User newUser = User.builder()
                .name(user.getName())
                .email(user.getEmail())
                .password(passwordEncoder.encode(user.getPassword()))
                .role(userRole)
                .build();

        userRepository.save(newUser);

        String token = jwtUtil.generateToken(user.getEmail());
        return AuthResponse.builder()
        .userId(user.getId())
                .token(token)
                .build();
    }

    public int getUserId(String token) {
        try {
            String user = jwtUtil.extractUsername(token);
            return userRepository.findByEmail(user)
                    .orElseThrow(() -> new UnauthorizedException("Usuario no encontrado")).getId();

        } catch (JwtException e) {
            throw new UnauthorizedException("Token inválido o expirado");

        } catch (NoSuchElementException e) {
            throw new UserNotFoundException("Usuario no encontrado para el token proporcionado");
        }

    }
}
