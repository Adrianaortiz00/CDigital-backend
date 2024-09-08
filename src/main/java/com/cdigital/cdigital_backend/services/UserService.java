package com.cdigital.cdigital_backend.services;

import com.cdigital.cdigital_backend.models.User;
import com.cdigital.cdigital_backend.models.Role;
import com.cdigital.cdigital_backend.repositories.UserRepository;
import com.cdigital.cdigital_backend.repositories.RoleRepository;
import com.cdigital.cdigital_backend.security.AuthResponse;
import com.cdigital.cdigital_backend.security.LoginRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;



    // Método para registrar un nuevo usuario
    public User registerUser(String name, String email, String password) {
        Optional<Role> roleOptional = roleRepository.findByNameRol("USER");
        Role userRole = roleOptional.orElseThrow(() -> new RuntimeException("Role not found"));

        User newUser = new User(name, email, encodePassword(password), userRole, new ArrayList<>());
        return userRepository.save(newUser);
    }

    // Método para obtener todos los usuarios
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Método para obtener un usuario por email
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }

    // Método para autenticar un usuario
    public AuthResponse login(LoginRequest loginRequest) {
        try {
            // Realizar la autenticación
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword())
            );

            // Si la autenticación es exitosa, se puede proceder
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            // Generar un token (este método debe ser implementado según tu lógica)
            String token = generateToken(userDetails);

            // Crear y retornar la respuesta de autenticación
            return new AuthResponse(token);
        } catch (Exception e) {
            throw new RuntimeException("Invalid credentials");
        }
    }

    // Método para codificar la contraseña
    public String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }

    // Método para generar un token (implementación ejemplo)
    private String generateToken(UserDetails userDetails) {
        // Implementar lógica de generación de token JWT o similar
        return "dummyToken";
    }
}
