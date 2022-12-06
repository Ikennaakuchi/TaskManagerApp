package com.taskmanager.taskmanagerapp.repositories;

import com.taskmanager.taskmanagerapp.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsernameAndPassword(String username, String password);
    Optional<User> findUserByUsername(String username);
    void deleteByUsername(String username);
}
