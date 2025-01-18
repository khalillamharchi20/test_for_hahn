package com.example.demo.service;

import java.util.Optional;

import com.example.demo.dto.RoleDto;
import com.example.demo.entity.Role;
import com.example.demo.entity.RoleName;

public interface RoleService {
    Role createRole(RoleDto roleDto);

    Optional<Role> findByRoleName(RoleName roleName);
}