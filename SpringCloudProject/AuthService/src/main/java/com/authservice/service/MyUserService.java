package com.authservice.service;

import com.authservice.dto.AuthRequest;
import com.authservice.dto.AuthResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.authservice.dto.MyUserDTO;
import com.authservice.model.MyUser;
import com.authservice.repository.MyUserRepository;

import java.util.ArrayList;
import java.util.List;


@Service
public class MyUserService implements IMyUserService {
	@Autowired
    private MyUserRepository myUserRepository;

	private final JwtUtil jwt;

	@Autowired
    public MyUserService(MyUserRepository myUserRepository, JwtUtil jwt){
        this.myUserRepository = myUserRepository;
		this.jwt = jwt;
	}

	@Override
	public MyUserDTO findMyUserByUsername(String username) {
		MyUser myUser = this.myUserRepository.findByUsername(username);
		return new MyUserDTO(myUser.getUsername(), myUser.getPassword());
	}
	@Override
	public List<MyUserDTO> findAll() {
		List<MyUserDTO> dtos = new ArrayList<>();
		for(MyUser myUser: myUserRepository.findAll()) {
			dtos.add(new MyUserDTO(myUser.getUsername(), myUser.getPassword()));
		}
		return dtos;
	}

	@Override
	public AuthResponse login(AuthRequest authRequest) {

		MyUser myUser = this.myUserRepository.findByUsername(authRequest.getUsername());
		if(!BCrypt.checkpw(authRequest.getPassword(), myUser.getPassword())){
			return new AuthResponse("");
		}
		myUser.setRole("ROLE_USER");

		return new AuthResponse(jwt.generate(myUser, "ACCESS"));
	}

	public MyUserDTO register(MyUserDTO myUserDTO) {
		//do validation if user already exists
		MyUser myUser = this.myUserRepository.findByUsername(myUserDTO.getUsername());
		if(myUser != null){
			return new MyUserDTO();
		}

		myUserDTO.setPassword(BCrypt.hashpw(myUserDTO.getPassword(), BCrypt.gensalt()));
		myUserDTO.setRole("ROLE_USER");

		// TODO: check if username unique
		this.myUserRepository.save(new MyUser(myUserDTO.getUsername(), myUserDTO.getPassword(), myUserDTO.getRole()));

		return myUserDTO;
	}



//	public AuthResponse login(AuthRequest authRequest) {
//		//do validation if user already exists
//		authRequest.setPassword(BCrypt.hashpw(authRequest.getPassword(), BCrypt.gensalt()));
//
//		// TODO: check if username unique
//		String accessToken = jwt.generate(user, "ACCESS");
//		return new AuthResponse(accessToken);
//	}

}
