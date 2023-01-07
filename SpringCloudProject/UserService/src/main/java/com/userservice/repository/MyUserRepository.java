package com.userservice.repository;

import com.userservice.model.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MyUserRepository extends JpaRepository<MyUser, String> {

    MyUser findMyUserByUsername(String username);

    void deleteByUsername(String username);
}
