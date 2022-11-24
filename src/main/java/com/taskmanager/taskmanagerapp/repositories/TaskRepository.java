package com.taskmanager.taskmanagerapp.repositories;

import com.taskmanager.taskmanagerapp.entities.Task;
import com.taskmanager.taskmanagerapp.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findTasksByUserId(Long userId);
    List<Task> findTasksByUserIdAndStatus(Long userId, String taskStatus);
}
