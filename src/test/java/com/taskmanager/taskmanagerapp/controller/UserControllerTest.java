package com.taskmanager.taskmanagerapp.controller;

import com.taskmanager.taskmanagerapp.dto.LoginDto;
import com.taskmanager.taskmanagerapp.dto.SignUpDto;
import com.taskmanager.taskmanagerapp.services.UserService;
import com.taskmanager.taskmanagerapp.services.serviceImpl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.annotation.After;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.event.annotation.AfterTestExecution;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserControllerTest {

    @Autowired
    private UserController userController;

    @Test
    void signUp() {
        SignUpDto newUser = new SignUpDto();
        newUser.setFirstName("Jack");
        newUser.setLastName("Remy");
        newUser.setUsername("jackremy");
        newUser.setPassword("1234");

        ResponseEntity<String> result = userController.signUp(newUser);
        assertEquals(new ResponseEntity<>("Sign up successful", HttpStatus.CREATED), result);
    }

    @AfterEach
    void tearDown() {
        userController.deleteUser("jackremy");
    }

//    @Test
//    void successfulLogin(){
//        LoginDto user = new LoginDto();
//        user.setUsername("iykejosh");
//        user.setPassword("1234");
//        ResponseEntity<String> result = userController.login(user);
//        assertEquals(new ResponseEntity<>("Login Successful", HttpStatus.OK), result);
//    }
//
//    @Test
//    void unSuccessfulLogin(){
//        LoginDto user = new LoginDto();
//        user.setUsername("");
//        user.setPassword("1234");
//        ResponseEntity<String> result = userController.login(user);
//        assertEquals(new ResponseEntity<>("Incorrect Username or password", HttpStatus.BAD_REQUEST), result);
//    }

}