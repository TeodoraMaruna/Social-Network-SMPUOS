package com.authservice.service;

import com.authservice.dto.MyUserDTO;

import java.util.List;
public interface IMyUserService {
	MyUserDTO findMyUserByUsername(String username);
	List<MyUserDTO> findAll();
}
