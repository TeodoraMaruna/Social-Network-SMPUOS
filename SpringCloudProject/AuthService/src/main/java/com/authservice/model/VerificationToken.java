package com.authservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
public class VerificationToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String token;

    private Timestamp expiryDate;

    @OneToOne
    @JoinColumn(name = "user_username", referencedColumnName = "username")
    private MyUser user;

    public VerificationToken(String token, MyUser user) {
        this.token = token;
        this.user = user;
    }
}
