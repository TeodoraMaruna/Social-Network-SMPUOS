package com.userservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MyUser {

    @Id
    private String username;

//    @Column(name = "password", nullable = false)
//    private String password;

    @Column(name = "role")
    private String role;

    @Column(name = "isPublic")
    private Boolean isPublic;

    @Column(name = "firstName")
    private String firstName;

    @Column(name = "lastName")
    private String lastName;

    @Column(name = "gender")
    private String gender;

    @Column(name = "email")
    private String email;

    @Column(name = "phoneNumber")
    private String phoneNumber;

    @Column(name = "isRegistered")
    private Boolean isRegistered;

}
