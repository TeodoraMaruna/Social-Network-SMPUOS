package com.authservice.model;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter 
@Getter
@Entity
public class MyUser {
	@Id
    private String username;
    @Column(name = "password", nullable = false)
    private String password;
    @Column(name = "role")
    private String role;


}
