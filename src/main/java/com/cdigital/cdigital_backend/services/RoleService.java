package com.cdigital.cdigital_backend.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.cdigital.cdigital_backend.models.Role;
import com.cdigital.cdigital_backend.repositories.RoleRepository;

@Service
public class RoleService {

    
    private RoleRepository roleRepository;

    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    public Optional<Role> getRoleById(int id) {
        return roleRepository.findById(id);
    }

    public Role saveRole(Role role) {
        return roleRepository.save(role);
    }

    public void deleteRole(int id) {
        roleRepository.deleteById(id);
    }
}
