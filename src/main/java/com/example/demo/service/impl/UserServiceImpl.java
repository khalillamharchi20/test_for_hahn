package com.example.demo.service.impl;


import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.context.annotation.Profile;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.dto.UserDto;
import com.example.demo.entity.Department;
import com.example.demo.entity.Role;
import com.example.demo.entity.RoleName;
import com.example.demo.entity.User;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder BCryptPasswordEncoder;
    

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository,  BCryptPasswordEncoder BCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.BCryptPasswordEncoder = BCryptPasswordEncoder;
    }

    @Override
    public User createUser(UserDto userDto) {
        if (userRepository.findByUsername(userDto.getUsername()).isPresent()) {
            throw new IllegalArgumentException("Username already exists: " + userDto.getUsername());
        }

        Set<Role> roles = new HashSet<>();
        for (String roleName : userDto.getRoles()) {
            Role role = roleRepository.findByRoleName(Enum.valueOf(RoleName.class, roleName))
                    .orElseThrow(() -> new IllegalArgumentException("Role not found: " + roleName));
            roles.add(role);
        }

        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setPassword(BCryptPasswordEncoder.encode(userDto.getPassword()));
        user.setRoles(roles);
        user.setDepartment(Enum.valueOf(Department.class, userDto.getDepartment()));
        return userRepository.save(user);
    }

    @Override
    public User updateUser(Long id, UserDto userDto) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + id));

        if (userDto.getUsername() != null) {
            if (!existingUser.getUsername().equals(userDto.getUsername()) &&
                    userRepository.findByUsername(userDto.getUsername()).isPresent()) {
                throw new IllegalArgumentException("Username already exists: " + userDto.getUsername());
            }
            existingUser.setUsername(userDto.getUsername());
        }

        if (userDto.getPassword() != null) {
            existingUser.setPassword(BCryptPasswordEncoder.encode(userDto.getPassword()));
        }

        if (userDto.getRoles() != null) {
            Set<Role> roles = new HashSet<>();
            for (String roleName : userDto.getRoles()) {
                Role role = roleRepository.findByRoleName(Enum.valueOf(RoleName.class, roleName))
                        .orElseThrow(() -> new IllegalArgumentException("Role not found: " + roleName));
                roles.add(role);
            }
            existingUser.setRoles(roles);
        }

        if (userDto.getDepartment() != null) {
            existingUser.setDepartment(Enum.valueOf(Department.class, userDto.getDepartment()));
        }

        return userRepository.save(existingUser);
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + id));
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public void deleteUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + id));
        userRepository.delete(user);
    }

    
}