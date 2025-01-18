package com.example.demo.service.impl;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.dto.RoleDto;
import com.example.demo.entity.Role;
import com.example.demo.entity.RoleName;
import com.example.demo.repository.RoleRepository;
import com.example.demo.service.RoleService;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role createRole(RoleDto roleDto) {
        Optional<Role> existingRole = roleRepository.findByRoleName(roleDto.getRoleName());
        if (existingRole.isPresent()) {
            throw new IllegalArgumentException("Role already exists: " + roleDto.getRoleName());
        }
        Role role = new Role();
        role.setRoleName(roleDto.getRoleName());
        return roleRepository.save(role);
    }

    @Override
    public Optional<Role> findByRoleName(RoleName roleName) {
        return roleRepository.findByRoleName(roleName);
    }
}