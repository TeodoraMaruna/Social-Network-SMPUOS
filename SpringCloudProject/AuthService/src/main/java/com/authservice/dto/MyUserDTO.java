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
    private String password;
    private String role;
    private Boolean isPublic;
    private Boolean isPublicStatus;
    private String firstName;
    private String gender;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String sagaStatus;
    private Boolean isRegistered;

    public MyUserDTO(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
