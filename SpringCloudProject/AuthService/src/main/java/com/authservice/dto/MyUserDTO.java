package com.authservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter 
@Getter
public class MyUserDTO {

    private String username;
    private boolean isPublic;
    private String password;
    private String role;
    private String firstName;
    private String lastName;
    private String gender;
    private String email;
    private String phoneNumber;

    public MyUserDTO(String username, String password) {
    }
}
