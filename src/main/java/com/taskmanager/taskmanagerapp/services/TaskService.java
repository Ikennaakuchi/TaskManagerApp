package com.taskmanager.taskmanagerapp.services;

import com.taskmanager.taskmanagerapp.dto.TaskDto;
import com.taskmanager.taskmanagerapp.entities.Task;
import com.taskmanager.taskmanagerapp.enums.Status;

import java.util.List;

public interface TaskService {

    Task createTask(TaskDto taskDto, Long userId);
    public Task editTask(Long taskId, TaskDto taskDto);
    List<TaskDto> viewAllTasks(Long userId);
    List<TaskDto> viewPendingTasks(Long userId);
    List<TaskDto> viewTasksInProgress(Long userId);
    List<TaskDto> viewDoneTasks(Long userId);
    public Task moveTaskToPending(Long taskId);
    Task moveTaskToDone(Long taskId);
    TaskDto viewTask(Long taskId);
    void deleteTask(Long taskId);
    void deleteTaskByTitle(String title);
}
