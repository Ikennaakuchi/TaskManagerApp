package com.taskmanager.taskmanagerapp.repositories;

import com.taskmanager.taskmanagerapp.entities.Task;
import com.taskmanager.taskmanagerapp.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findTasksByUserId(Long userId);
    List<Task> findTasksByUserIdAndStatus(Long userId, String taskStatus);
    void deleteTaskByTitle(String title);


}
