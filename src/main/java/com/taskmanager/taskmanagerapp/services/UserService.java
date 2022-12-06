package com.taskmanager.taskmanagerapp.services;

import com.taskmanager.taskmanagerapp.dto.LoginDto;
import com.taskmanager.taskmanagerapp.dto.SignUpDto;
import com.taskmanager.taskmanagerapp.entities.User;

import java.util.List;

public interface UserService {

    User signUp(SignUpDto signUpDto);
    User login(LoginDto loginDto);
    List<SignUpDto> getAllUsers();
    void deleteUserbyU(String username);
    void deleteUserById(Long userId);
}
