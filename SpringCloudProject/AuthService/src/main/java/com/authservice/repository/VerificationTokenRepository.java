package com.authservice.repository;

import com.authservice.model.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VerificationTokenRepository extends JpaRepository<VerificationToken,Integer> {

    VerificationToken findByToken(String token);
    VerificationToken findByUser_Username(String username);
    void deleteByUser_Username(String username);
}

