package com.authservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.authservice.model.MyUser;
@Repository
public interface MyUserRepository extends JpaRepository<MyUser, String>{
	MyUser findByUsername(String username);
}
