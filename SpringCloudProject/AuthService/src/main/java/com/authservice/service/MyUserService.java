package com.authservice.service;

import com.authservice.dto.AuthRequest;
import com.authservice.dto.AuthResponse;
import com.authservice.repository.VerificationTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import com.authservice.dto.MyUserDTO;
import com.authservice.model.MyUser;
import com.authservice.repository.MyUserRepository;
import org.springframework.web.client.RestTemplate;

import javax.mail.MessagingException;
import javax.transaction.Transactional;
import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Service
public class MyUserService implements IMyUserService {
	@Autowired
    private MyUserRepository myUserRepository;
	@Autowired
	private VerificationTokenRepository verificationTokenRepository;
	@Autowired
	private VerificationTokenService verificationTokenService;
	@Autowired
	private EmailService emailService;
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
		if(!BCrypt.checkpw(authRequest.getPassword(), myUser.getPassword()) || myUser.getIsRegistered() == false){
			return new AuthResponse("");
		}
		myUser.setRole("ROLE_USER");

		return new AuthResponse(jwt.generate(myUser, "ACCESS"));
	}

	@Override
	public void activateUser(String username) throws UserPrincipalNotFoundException {
		MyUser myUser = this.myUserRepository.findByUsername(username);
		if(myUser == null){
			throw new UserPrincipalNotFoundException("username not found");
		}
		myUser.setIsRegistered(true);
		this.myUserRepository.save(myUser);
		try{
			restTemplate.put("http://localhost:9000/user-service/editRegisterStatus/" + username, myUser);
		}catch (Exception e){}

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

		Boolean isUnique = restTemplate.postForObject("http://localhost:9000/user-service/check", myUserDTO, Boolean.class);

		if(!isUnique){
			return new MyUserDTO();
		}

		try {
			myUser = this.myUserRepository.save(new MyUser(myUserDTO.getUsername(), myUserDTO.getPassword(), myUserDTO.getRole(),false));
			//this.sendVerificationEmail(myUser, myUserDTO.getEmail());
		}catch (Exception e){
			return new MyUserDTO();
		}

		this.sagaOrchestrator("AUTH_SERVICE_CREATED", myUserDTO);

		myUserDTO.setSagaStatus(this.transactionStatus);
		this.transactionStatus = "";

		return myUserDTO;
	}


	@Transactional
	public void sagaOrchestrator(String sagaStatus, MyUserDTO myUserDTO){

		switch(sagaStatus) {
			case "AUTH_SERVICE_CREATED":
				try {
					MyUserDTO myUserDTO1 = restTemplate.postForObject("http://localhost:9000/connection-service/registerUserConnection", myUserDTO, MyUserDTO.class);
					this.sagaStatus = myUserDTO1.getSagaStatus();
					System.out.println(this.sagaStatus);
				}catch (Exception e){
					this.sagaStatus = "AUTH_SERVICE_ROLLBACK";
				}
				break;
			case "CONNECTION_SERVICE_CREATED":
				try {
					MyUserDTO myUserDTO1 = restTemplate.postForObject("http://localhost:9000/user-service/add", myUserDTO, MyUserDTO.class);
					this.sagaStatus = myUserDTO1.getSagaStatus();
					System.out.println(this.sagaStatus);
				}catch (Exception e){
					this.sagaStatus = "CONNECTION_SERVICE_ROLLBACK";
				}
				break;
			case "AUTH_SERVICE_ROLLBACK":
				this.transactionStatus = "FAILED";
				this.verificationTokenRepository.deleteByUser_Username(myUserDTO.getUsername());
				this.myUserRepository.deleteByUsername(myUserDTO.getUsername());
				break;
			case "CONNECTION_SERVICE_ROLLBACK":
				try {
					restTemplate.delete("http://localhost:9000/connection-service/deleteUserConnection/" + myUserDTO.getUsername());
					this.sagaStatus = "AUTH_SERVICE_ROLLBACK";
					System.out.println(this.sagaStatus);
				}catch (Exception e){
					this.sagaStatus = "CONNECTION_SERVICE_ROLLBACK";
				}
				break;
			case "USER_SERVICE_CREATED":
				this.transactionStatus = "SUCCESS";
				System.out.println(this.sagaStatus);
				break;
		}

		if(!(this.transactionStatus.equals("SUCCESS") || this.transactionStatus.equals("FAILED"))) {
			this.sagaOrchestrator(this.sagaStatus, myUserDTO);
		}
	}


	public void sendVerificationEmail(MyUser myUser, String email) throws MessagingException {    // preko mejla saljemo verifikacioni token

		if (myUser != null){
			try {
				System.out.println("uslooo");
				String token = UUID.randomUUID().toString();
				// kreiranje verifikacionog tokena
				verificationTokenService.save(myUser, token);
				this.emailService.sendHTMLMail(myUser, email);
			} catch (Exception e) {
				e.printStackTrace();
				throw e;
			}
		}
	}

}
