package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.UserDto;
import com.example.demo.entity.User;

public interface UserService {
    User createUser(UserDto userDto);
    
    User updateUser(Long id, UserDto userDto);

    User getUserById(Long id);

    List<User> getAllUsers();

    void deleteUser(Long id);
}