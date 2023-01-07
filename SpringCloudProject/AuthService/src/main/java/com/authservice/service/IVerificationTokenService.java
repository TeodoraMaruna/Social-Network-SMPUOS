package com.authservice.service;

import com.authservice.model.MyUser;
import com.authservice.model.VerificationToken;

import java.sql.Timestamp;

public interface IVerificationTokenService {

    VerificationToken findByToken(String token);

    VerificationToken findByUser_Username(String username);

    void save(MyUser user, String token);

    Timestamp calculateExpiryDate(int expiryTimeInMinutes);
}
