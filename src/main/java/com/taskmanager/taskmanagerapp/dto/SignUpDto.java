package com.taskmanager.taskmanagerapp.dto;

import lombok.*;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@ToString
public class SignUpDto {
    private String firstName;
    private String lastName;
    private String username;
    private String password;
}
