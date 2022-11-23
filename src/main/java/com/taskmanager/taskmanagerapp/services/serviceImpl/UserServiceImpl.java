package com.taskmanager.taskmanagerapp.services.serviceImpl;

import com.taskmanager.taskmanagerapp.dto.LoginDto;
import com.taskmanager.taskmanagerapp.dto.SignUpDto;
import com.taskmanager.taskmanagerapp.entities.User;
import com.taskmanager.taskmanagerapp.exception.ResourceAlreadyExists;
import com.taskmanager.taskmanagerapp.repositories.UserRepository;
import com.taskmanager.taskmanagerapp.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User signUp(SignUpDto signUpDto) {
        if(userRepository.findUserByUsername(signUpDto.getUsername()).isPresent()){
            throw new ResourceAlreadyExists("Username already exists");
        }
        User user = new User();
        BeanUtils.copyProperties(signUpDto, user);
        return userRepository.save(user);
    }

    @Override
    public User login(LoginDto loginDto) {
        User user = userRepository.findByUsernameAndPassword(loginDto.getUsername(), loginDto.getPassword()).orElse(null);
        return user;
    }

    @Override
    public List<SignUpDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        List<SignUpDto> userList = new ArrayList<>();
        for (User user: users){
            SignUpDto signUpDto = new SignUpDto();
            BeanUtils.copyProperties(user, signUpDto);
            userList.add(signUpDto);
        }
        return userList;
    }


}
