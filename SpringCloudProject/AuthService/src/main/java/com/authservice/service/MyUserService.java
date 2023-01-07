package com.authservice.service;

import com.authservice.dto.AuthRequest;
import com.authservice.dto.AuthResponse;
import com.authservice.dto.PostDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.authservice.dto.MyUserDTO;
import com.authservice.model.MyUser;
import com.authservice.repository.MyUserRepository;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;


@Service
public class MyUserService implements IMyUserService {
	@Autowired
    private MyUserRepository myUserRepository;
	@Autowired
	private RestTemplate restTemplate;
	private String transactionStatus = "";
	private String sagaStatus;

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
		if(myUser == null){
			return new AuthResponse("");
		}
		if(!BCrypt.checkpw(authRequest.getPassword(), myUser.getPassword())){
			return new AuthResponse("");
		}
		myUser.setRole("ROLE_USER");

		return new AuthResponse(jwt.generate(myUser, "ACCESS"));
	}

	@Transactional
	public MyUserDTO register(MyUserDTO myUserDTO) {
		//do validation if user already exists
		MyUser myUser = this.myUserRepository.findByUsername(myUserDTO.getUsername());
		if(myUser != null){
			return new MyUserDTO();
		}

		myUserDTO.setPassword(BCrypt.hashpw(myUserDTO.getPassword(), BCrypt.gensalt()));
		myUserDTO.setRole("ROLE_USER");

		try {
			this.myUserRepository.save(new MyUser(myUserDTO.getUsername(), myUserDTO.getPassword(), myUserDTO.getRole()));

		}catch (Exception e){
			return new MyUserDTO();
		}
		this.sagaOrchestrator("AUTH_SERVICE_CREATED", myUserDTO);

		myUserDTO.setSagaStatus(this.transactionStatus);
		return myUserDTO;
	}


	public void sagaOrchestrator(String sagaStatus, MyUserDTO myUserDTO){

		switch(sagaStatus) {
			case "AUTH_SERVICE_CREATED":
				try {
					MyUserDTO myUserDTO1 = restTemplate.postForObject("http://localhost:9000/connection-service/registerUserConnection", myUserDTO, MyUserDTO.class);
					this.sagaStatus = myUserDTO1.getSagaStatus();
				}catch (Exception e){
					this.sagaStatus = "AUTH_SERVICE_ROLLBACK";
				}
				break;
			case "CONNECTION_SERVICE_CREATED":
				try {
					MyUserDTO myUserDTO1 = restTemplate.postForObject("http://localhost:9000/user-service/add", myUserDTO, MyUserDTO.class);
					this.sagaStatus = myUserDTO1.getSagaStatus();
				}catch (Exception e){
					this.sagaStatus = "CONNECTION_SERVICE_ROLLBACK";
				}
				break;
			case "AUTH_SERVICE_ROLLBACK":
				this.transactionStatus = "FAILED";
				this.myUserRepository.deleteByUsername(myUserDTO.getUsername());
				break;
			case "CONNECTION_SERVICE_ROLLBACK":
				try {
					restTemplate.delete("http://localhost:9000/connection-service/deleteUserConnection/" + myUserDTO.getUsername());
					this.sagaStatus = "AUTH_SERVICE_ROLLBACK";
				}catch (Exception e){
					this.sagaStatus = "CONNECTION_SERVICE_ROLLBACK";
				}
				break;
			case "USER_SERVICE_CREATED":
				this.transactionStatus = "SUCCESS";
				break;
		}

		if(!(this.transactionStatus.equals("SUCCESS") || this.transactionStatus.equals("FAILED"))) {
			this.sagaOrchestrator(this.sagaStatus, myUserDTO);
		}
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
