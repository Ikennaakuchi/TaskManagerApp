package com.taskmanager.taskmanagerapp.controller;

import com.taskmanager.taskmanagerapp.dto.TaskDto;
import com.taskmanager.taskmanagerapp.entities.Task;
import com.taskmanager.taskmanagerapp.services.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/task")
public class TaskController {

    private final TaskService taskService;

    @PostMapping("/create-task/{userId}")
    public ResponseEntity<String> createTask(@RequestBody TaskDto taskDto,@PathVariable Long userId){
        taskService.createTask(taskDto, userId);
        return new ResponseEntity<>("Task added successfully", HttpStatus.CREATED);
    }

    @PostMapping("/edit-task/{taskId}")
    public ResponseEntity<String> editTask(@PathVariable Long taskId, @RequestBody TaskDto taskDto){
        taskService.editTask(taskId, taskDto);
        return new ResponseEntity<>("Task updated successfully", HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<TaskDto>> viewAllTasks(@PathVariable Long userId){
        List<TaskDto> taskDtoList = taskService.viewAllTasks(userId);
        return new ResponseEntity<>(taskDtoList, HttpStatus.OK);
    }

    @GetMapping("/my-task/{taskId}")
    public ResponseEntity<TaskDto> viewTask(@PathVariable Long taskId){
        TaskDto taskDto = taskService.viewTask(taskId);
        return new ResponseEntity<>(taskDto, HttpStatus.OK);
    }

    @GetMapping("/pending-tasks/{userId}")
    private ResponseEntity<List<TaskDto>> viewPending(@PathVariable Long userId){
        List<TaskDto> taskDtoList = taskService.viewPendingTasks(userId);
        return new ResponseEntity<>(taskDtoList, HttpStatus.OK);
    }

    @GetMapping("/done-tasks/{userId}")
    private ResponseEntity<List<TaskDto>> viewDone(@PathVariable Long userId){
        List<TaskDto> taskDtoList = taskService.viewDoneTasks(userId);
        return new ResponseEntity<>(taskDtoList, HttpStatus.OK);
    }

    @GetMapping("/tasks-in-progress/{userId}")
    private ResponseEntity<List<TaskDto>> viewTasksInProgress(@PathVariable Long userId){
        List<TaskDto> taskDtoList = taskService.viewTasksInProgress(userId);
        return new ResponseEntity<>(taskDtoList, HttpStatus.OK);
    }

    @PutMapping("/move-to-pending/{taskId}")
    private ResponseEntity<String> moveToPending(@PathVariable Long taskId){
        taskService.moveTaskToPending(taskId);
        return new ResponseEntity<>("Task moved to pending", HttpStatus.OK);
    }

    @PutMapping("/move-to-done/{taskId}")
    private ResponseEntity<String> moveToDone(@PathVariable Long taskId){
        taskService.moveTaskToDone(taskId);
        return new ResponseEntity<>("Task moved to done", HttpStatus.OK);
    }
    @DeleteMapping("/delete/{taskId}")
    public ResponseEntity<String> deleteTask(@PathVariable Long taskId){
        taskService.deleteTask(taskId);
        return new ResponseEntity<>("Task deleted successfully", HttpStatus.NO_CONTENT);
    }

}
