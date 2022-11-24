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
import java.util.Optional;

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
        task.setStatus(Status.IN_PROGRESS.name());
        task.setCreatedAt(LocalDateTime.now());
        task.setUser(user);
        return taskRepository.save(task);
    }

    @Override
    public Task editTask(Long taskId, TaskDto taskDto) {
        Task task = taskRepository.findById(taskId).get();
        BeanUtils.copyProperties(taskDto, task);
        task.setStatus(Status.IN_PROGRESS.name());
        task.setUpdatedAt(LocalDateTime.now());
        return taskRepository.save(task);
    }

    @Override
    public List<TaskDto> viewAllTasks(Long userId) {
        List<Task> tasks = taskRepository.findTasksByUserId(userId);
        if(tasks.isEmpty()){
            throw new ResourceNotFoundException("Not found");
        }
        return getTaskDtos(tasks);
    }

    private List<TaskDto> getTaskDtos(List<Task> tasks) {
        List<TaskDto> taskDtoList = new ArrayList<>();
        for (Task task: tasks){
          TaskDto taskDto = new TaskDto();
          BeanUtils.copyProperties(task, taskDto);
          taskDtoList.add(taskDto);
        }
        return taskDtoList;
    }

    @Override
    public List<TaskDto> viewPendingTasks(Long userId) {
        List<Task> taskList = taskRepository.findTasksByUserIdAndStatus(userId, Status.PENDING.name());
        if(taskList.isEmpty()){
            throw new ResourceNotFoundException("Not found");
        }
        return getTaskDtos(taskList);
    }

    @Override
    public List<TaskDto> viewTasksInProgress(Long userId) {
        List<Task> taskList = taskRepository.findTasksByUserIdAndStatus(userId, Status.IN_PROGRESS.name());
        if(taskList.isEmpty()){
            throw new ResourceNotFoundException("Not found");
        }
        return getTaskDtos(taskList);
    }

    @Override
    public List<TaskDto> viewDoneTasks(Long userId) {
        List<Task> taskList = taskRepository.findTasksByUserIdAndStatus(userId, Status.DONE.name());
        if(taskList.isEmpty()){
            throw new ResourceNotFoundException("Not found");
        }
        return getTaskDtos(taskList);
    }

    @Override
    public Task moveTaskToPending(Long taskId) {;
        Task task = taskRepository.findById(taskId).orElseThrow(()-> new ResourceNotFoundException("Not found"));
        task.setStatus(Status.PENDING.name());
        task.setUpdatedAt(LocalDateTime.now());
        return taskRepository.save(task);
    }

    @Override
    public Task moveTaskToDone(Long taskId) {
        Task task = taskRepository.findById(taskId).orElseThrow(()-> new ResourceNotFoundException("Not found"));
        task.setStatus(Status.DONE.name());
        task.setUpdatedAt(LocalDateTime.now());
        return taskRepository.save(task);
    }

    @Override
    public TaskDto viewTask(Long taskId) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(()-> new ResourceNotFoundException("Task not found"));
        TaskDto taskDto = new TaskDto();
        BeanUtils.copyProperties(task, taskDto);
        return taskDto;
    }

    @Override
    public void deleteTask(Long taskId) {
        taskRepository.deleteById(taskId);
    }
}
