package com.userservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MyUserDTO {
    private String username;
//    private String password;
    private String role;
    private Boolean isPublic;
    private String firstName;
    private String gender;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String sagaStatus;
}
