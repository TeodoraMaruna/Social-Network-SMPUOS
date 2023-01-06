package com.connectionservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MyUserDTO {

    // TODO: delete class
    private String username;
    private String role;
    private Boolean isPublic;
    private String firstName;
    private String gender;
    private String lastName;
    private String email;
    private String phoneNumber;
}

