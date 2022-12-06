package com.taskmanager.taskmanagerapp.services.serviceImpl;

import com.taskmanager.taskmanagerapp.dto.TaskDto;
import com.taskmanager.taskmanagerapp.entities.Task;
import com.taskmanager.taskmanagerapp.entities.User;
import com.taskmanager.taskmanagerapp.enums.Status;
import com.taskmanager.taskmanagerapp.exception.ResourceNotFoundException;
import com.taskmanager.taskmanagerapp.repositories.TaskRepository;
import com.taskmanager.taskmanagerapp.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.BeanUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class TaskServiceImplTest {

    @Mock
    TaskRepository taskRepository;

    @Mock
    UserRepository userRepository;

    @InjectMocks
    TaskServiceImpl taskService;
    User user;
    Task task;
    Task task1;
    Task task2;
    Task task3;
    Task task4;

    TaskDto taskDto;
    List<Task> pendingTaskList;
    List<Task> taskInProgressList;
    List<Task> doneTaskList;
    List<Task> taskList;



    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);
        user = new User();
        user.setFirstName("Iyke");
        user.setLastName("Josh");
        user.setUsername("iykejosh");
        user.setPassword("1234");
        user.setId(2L);

        taskDto = new TaskDto();
//        taskDto.setTitle("Reading");
        taskDto.setDescription("Excercise");

        task = new Task();
        task.setId(1L);
        task.setStatus(Status.PENDING.name());
        task.setUser(user);
        task.setTitle("Reading");

        task1 = new Task();
        task1.setId(2L);
        task1.setStatus(Status.PENDING.name());
        task1.setUser(user);
        task1.setTitle("Running");

        task2 = new Task();
        task2.setId(3L);
        task.setStatus(Status.IN_PROGRESS.name());
        task2.setUser(user);
        task2.setTitle("Swimming");

        task3 = new Task();
        task3.setId(4L);
        task3.setStatus(Status.PENDING.name());
        task3.setUser(user);
        task3.setTitle("Traveling");

        task4 = new Task();
        task4.setId(5L);
        task4.setStatus(Status.DONE.name());
        task4.setUser(user);
        task4.setTitle("Traveling");


        taskList = new ArrayList<>();
        taskList.add(task);
        taskList.add(task1);
        taskList.add(task2);
        taskList.add(task3);

        pendingTaskList = new ArrayList<>();
        pendingTaskList.add(task);
        pendingTaskList.add(task1);

        taskInProgressList = new ArrayList<>();
        taskInProgressList.add(task2);

        doneTaskList = new ArrayList<>();
        doneTaskList.add(task3);

    }

    @Test
    void createTask() {
        when(userRepository.findById(anyLong()))
                .thenReturn(Optional.of(user));
        when(taskRepository.save(any(Task.class)))
                .thenReturn(task);

        Task createdTask = taskService.createTask(taskDto, 1L);

        assertNotNull(createdTask);
        assertEquals(1L, createdTask.getId());
        assertEquals(task.getTitle(), createdTask.getTitle());
    }

    @Test
    void editTask() {
        when(taskRepository.findById(anyLong()))
                .thenReturn(Optional.of(task));
        when(taskRepository.save(any(Task.class)))
                .thenReturn(task);

        Task editedTask = taskService.editTask(1L, taskDto);
        assertNotNull(editedTask);
    }

    @Test
    void viewAllTasks() {
        when(taskRepository.findTasksByUserId(anyLong()))
                .thenReturn(taskList);

        List<TaskDto> taskDtos = taskService.viewAllTasks(2L);
        assertEquals(4, taskDtos.size());
    }

    @Test
    void viewPendingTasks() {
        when(taskRepository.findTasksByUserIdAndStatus(anyLong(), anyString()))
                .thenReturn(pendingTaskList);

        List<TaskDto> pendingTaskDtos = taskService.viewPendingTasks(2L);
        assertEquals(2, pendingTaskDtos.size());
    }

    @Test
    void viewTasksInProgress() {
        when(taskRepository.findTasksByUserIdAndStatus(anyLong(), anyString()))
                .thenReturn(taskInProgressList);

        List<TaskDto> taskInProgress = taskService.viewTasksInProgress(2L);
        assertEquals(1, taskInProgress.size());
    }

    @Test
    void viewDoneTasks() {
        when(taskRepository.findTasksByUserIdAndStatus(anyLong(), anyString()))
                .thenReturn(pendingTaskList);

        List<TaskDto> pendingTaskDtos = taskService.viewPendingTasks(2L);
        assertEquals(2, pendingTaskDtos.size());
    }

    @Test
    void moveTaskToPending() {
        when(taskRepository.findById(anyLong()))
                .thenReturn(Optional.of(task));
        when(taskRepository.save(any(Task.class)))
                .thenReturn(task3);

        Task movedTask = taskService.moveTaskToPending(5L);
        assertNotNull(movedTask);
        assertEquals("PENDING", movedTask.getStatus());
    }

    @Test
    void moveTaskToDone() {
        when(taskRepository.findById(anyLong()))
                .thenReturn(Optional.of(task));
        when(taskRepository.save(any(Task.class)))
                .thenReturn(task4);

        Task movedTask = taskService.moveTaskToDone(4L);
        assertNotNull(movedTask);
        assertEquals("DONE", movedTask.getStatus());
    }

    @Test
    void viewTask() {
        when(taskRepository.findById(anyLong()))
                .thenReturn(Optional.of(task));

        TaskDto viewedTask = taskService.viewTask(1L);
        assertNotNull(viewedTask);
        assertEquals(task.getTitle(), viewedTask.getTitle());
    }

    @Test
    void deleteTask() {
        taskService.deleteTask(5L);
        verify(taskRepository, times(1)).deleteById(5L);
    }
}