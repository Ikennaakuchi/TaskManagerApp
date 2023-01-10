package com.taskmanager.taskmanagerapp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.taskmanager.taskmanagerapp.dto.LoginDto;
import com.taskmanager.taskmanagerapp.dto.SignUpDto;
import com.taskmanager.taskmanagerapp.services.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
public class UserControllerMock {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper mapper;
    @MockBean
    private UserService userService;

    @Test
    void signUp(){
       try {
           SignUpDto newUser = new SignUpDto();
           newUser.setFirstName("Mack");
           newUser.setLastName("Remy");
           newUser.setUsername("mackremy");
           newUser.setPassword("1234");

           String requestBody = mapper.writeValueAsString(newUser);

           mockMvc.perform(post("/user/sign-up")
                           .contentType("application/json").content(requestBody))
                   .andExpect(status().isCreated());

       }catch (Exception ex){
           ex.printStackTrace();
       }
    }

    @Test
    void login(){
        try{
            LoginDto loginDto = new LoginDto();
            loginDto.setUsername("mackremy");
            loginDto.setPassword("1234");

            String requestBody = mapper.writeValueAsString(loginDto);

            mockMvc.perform(post("/user/login")
                            .contentType("application/json").content(requestBody))
                    .andExpect(status().isOk());
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
