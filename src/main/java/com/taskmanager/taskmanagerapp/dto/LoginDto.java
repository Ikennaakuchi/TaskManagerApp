package com.taskmanager.taskmanagerapp.dto;

import lombok.*;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@ToString
public class LoginDto {
    private String username;
    private String password;
}
