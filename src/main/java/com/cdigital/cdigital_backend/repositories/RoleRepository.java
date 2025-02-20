package com.cdigital.cdigital_backend.repositories;

import com.cdigital.cdigital_backend.models.Role;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByNameRol(String nameRol);
}
