package com.taskmanager.taskmanagerapp.controller;

import com.taskmanager.taskmanagerapp.dto.SignUpDto;
import com.taskmanager.taskmanagerapp.dto.TaskDto;
import com.taskmanager.taskmanagerapp.entities.User;
import com.taskmanager.taskmanagerapp.services.TaskService;
import com.taskmanager.taskmanagerapp.services.UserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TaskControllerTest {

    @Autowired
    private TaskController taskController;
    @Autowired
    private UserService userService;
    @Autowired
    private TaskService taskService;

    @Test
    void createTask() {
        SignUpDto newUser = new SignUpDto();
        newUser.setFirstName("Nick");
        newUser.setLastName("Pope");
        newUser.setUsername("nick");
        newUser.setPassword("1234");
        User user = userService.signUp(newUser);

        TaskDto taskDto = new TaskDto();
        taskDto.setTitle("Test1");
        taskDto.setDescription("Random");


        ResponseEntity<String> result = taskController.createTask(taskDto, user.getId());
        assertEquals(new ResponseEntity<>("Task added successfully", HttpStatus.CREATED), result);
    }

    @AfterEach
    void tearDown() {
        taskService.deleteTaskByTitle("Test1");
        userService.deleteUserbyU("nick");
    }
}