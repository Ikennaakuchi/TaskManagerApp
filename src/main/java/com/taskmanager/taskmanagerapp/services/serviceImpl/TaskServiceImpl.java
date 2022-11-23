package com.taskmanager.taskmanagerapp.services.serviceImpl;

import com.taskmanager.taskmanagerapp.dto.TaskDto;
import com.taskmanager.taskmanagerapp.entities.Task;
import com.taskmanager.taskmanagerapp.entities.User;
import com.taskmanager.taskmanagerapp.enums.Status;
import com.taskmanager.taskmanagerapp.exception.ResourceNotFoundException;
import com.taskmanager.taskmanagerapp.repositories.TaskRepository;
import com.taskmanager.taskmanagerapp.repositories.UserRepository;
import com.taskmanager.taskmanagerapp.services.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    @Override
    public Task createTask(TaskDto taskDto, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(()-> new ResourceNotFoundException("User not found"));
        Task task = new Task();
        task.setTitle(taskDto.getTitle());
        task.setDescription(taskDto.getDescription());
        task.setStatus(Status.PENDING);
        task.setCreatedAt(LocalDateTime.now());
        task.setUpdatedAt(taskDto.getUpdatedAt());
        task.setCompletedAt(taskDto.getCompletedAt());
        task.setUser(user);
        return taskRepository.save(task);
    }

    @Override
    public Task editTask(Long taskId) {
        TaskDto taskDto = viewTask(taskId);
        Task task = new Task();
        BeanUtils.copyProperties(taskDto, task);
        return taskRepository.save(task);
    }

    @Override
    public List<TaskDto> viewAllTasks(Long userId) {
        List<Task> tasks = taskRepository.findTasksByUserId(userId);
        List<TaskDto> taskDtoList = new ArrayList<>();
      BeanUtils.copyProperties(tasks, taskDtoList);
        return taskDtoList;
    }

    @Override
    public List<TaskDto> viewPendingTasks(Long userId, Status taskStatus) {
        User user = userRepository.findById(userId)
                .orElseThrow(()-> new ResourceNotFoundException("User not found"));
        List<Task> tasks = taskRepository.findTasksByStatus()
    }

    @Override
    public List<TaskDto> viewTasksInProgress(Long userId) {
        return null;
    }

    @Override
    public List<TaskDto> viewDoneTasks(Long userId) {
        return null;
    }

    @Override
    public Task moveTaskToPending(Long taskId) {
        return null;
    }

    @Override
    public Task moveTaskToDone(Long taskId) {
        return null;
    }

    @Override
    public TaskDto viewTask(Long taskId) {
        Task task = taskRepository.findById(taskId).get();
        TaskDto taskDto = new TaskDto();
        BeanUtils.copyProperties(task, taskDto);
        return taskDto;
    }

    @Override
    public void deleteTask(Long taskId) {
        taskRepository.deleteById(taskId);
    }

}
