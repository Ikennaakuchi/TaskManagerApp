package com.taskmanager.taskmanagerapp.controller;

import com.taskmanager.taskmanagerapp.dto.LoginDto;
import com.taskmanager.taskmanagerapp.dto.SignUpDto;
import com.taskmanager.taskmanagerapp.entities.User;
import com.taskmanager.taskmanagerapp.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @PostMapping("/sign-up")
    public ResponseEntity<String> signUp(@RequestBody SignUpDto signUpDto){
        userService.signUp(signUpDto);
        return new ResponseEntity<>("Sign up successful", HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDto loginDto){
        User user = userService.login(loginDto);
        if (user == null){
            return new ResponseEntity<>("Incorrect Username or password", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Login Successful", HttpStatus.OK);
    }
    @GetMapping("/")
    public ResponseEntity<List<SignUpDto>> getAllUsers(){
        List<SignUpDto> signUpDtos = userService.getAllUsers();
        return new ResponseEntity<>(signUpDtos, HttpStatus.OK);
    }
}
