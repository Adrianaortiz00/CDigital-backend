package com.cdigital.cdigital_backend.repositories;

//import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cdigital.cdigital_backend.models.User;

public interface UserRepository extends JpaRepository<User, Integer> {
   // Optional<User> findfindByEmail(String email);
    
}
