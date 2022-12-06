package com.taskmanager.taskmanagerapp.services.serviceImpl;

import com.taskmanager.taskmanagerapp.dto.LoginDto;
import com.taskmanager.taskmanagerapp.dto.SignUpDto;
import com.taskmanager.taskmanagerapp.entities.User;
import com.taskmanager.taskmanagerapp.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class UserServiceImplTest {

    @Mock
    UserRepository userRepository;

    @InjectMocks
    UserServiceImpl userService;

    User user;
    User user1;
    User user2;
    List<User> userList;
    SignUpDto userDto1;
    SignUpDto userDto2;
    SignUpDto userDto3;
    List<SignUpDto> signUpDtos;
    LoginDto loginDto;

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);
        userDto1 = new SignUpDto();
        userDto1.setUsername("Mary");
        userDto1.setLastName("Jack");
        userDto1.setUsername("maryjack");
        userDto1.setPassword("1234");

        user = new User();
        user.setFirstName("Biggs");
        user.setLastName("Jack");
        user.setUsername("biggsjack");
        user.setPassword("1234");

        user1 = new User();
        user1.setFirstName("Mary");
        user1.setLastName("Jack");
        user1.setUsername("maryjack");
        user1.setPassword("1234");

        user2 = new User();
        user2.setFirstName("Romeo");
        user2.setLastName("Jack");
        user2.setUsername("romeojack");
        user2.setPassword("1234");

        userList = new ArrayList<>();
        userList.add(user);
        userList.add(user1);
        userList.add(user2);


        loginDto = new LoginDto();
        loginDto.setUsername("maryjack");
        loginDto.setPassword("1234");
    }

    @Test
    void signUp() {
        when(userRepository.save(any(User.class)))
                .thenReturn(user);

        User registeredUser = userService.signUp(userDto1);
        assertNotNull(registeredUser);
        assertEquals("Mary", registeredUser.getFirstName());
    }

    @Test
    void login() {
        when(userRepository.findByUsernameAndPassword(anyString(), anyString()))
                .thenReturn(Optional.of(user));

        User loggedInUser = userService.login(loginDto);
        assertNotNull(loggedInUser);
        assertEquals("Mary", loggedInUser.getFirstName());
    }

    @Test
    void getAllUsers() {
        when(userRepository.findAll())
                .thenReturn(userList);
        List<SignUpDto> users = userService.getAllUsers();
        assertEquals(3, users.size());
    }

    @Test
    void deleteUserById() {
        userService.deleteUserById(anyLong());
        verify(userRepository, times(1)).deleteById(anyLong());
    }
}