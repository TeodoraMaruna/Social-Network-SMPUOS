package com.authservice.service;

import com.authservice.model.MyUser;
import com.authservice.model.VerificationToken;
import com.authservice.repository.VerificationTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Calendar;

@Service
public class VerificationTokenService implements IVerificationTokenService {

    @Autowired
    private VerificationTokenRepository verificationTokenRepository;

    public VerificationToken findByToken(String token) {
        return this.verificationTokenRepository.findByToken(token);
    }

    @Override
    public VerificationToken findByUser_Username(String username) {
        return verificationTokenRepository.findByUser_Username(username);
    }

    public void save(MyUser user, String token) {
        VerificationToken verificationToken = new VerificationToken(token, user);
        verificationToken.setExpiryDate(this.calculateExpiryDate(60 * 24));
        this.verificationTokenRepository.save(verificationToken);
    }

    public Timestamp calculateExpiryDate(int expiryTimeInMinutes) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MINUTE, expiryTimeInMinutes);
        return new Timestamp(cal.getTime().getTime());
    }
}

