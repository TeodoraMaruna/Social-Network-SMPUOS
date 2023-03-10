package com.authservice.service;

import com.authservice.dto.AuthRequest;
import com.authservice.dto.AuthResponse;
import com.authservice.dto.MyUserDTO;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.List;
public interface IMyUserService {
	MyUserDTO findMyUserByUsername(String username);
	List<MyUserDTO> findAll();
	AuthResponse login(AuthRequest authRequest);
	void activateUser(String username) throws UserPrincipalNotFoundException;
}
