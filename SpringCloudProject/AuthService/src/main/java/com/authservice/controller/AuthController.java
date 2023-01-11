package com.authservice.controller;

import com.authservice.dto.ActivationDTO;
import com.authservice.dto.AuthRequest;
import com.authservice.dto.AuthResponse;
import com.authservice.dto.MyUserDTO;
import com.authservice.model.MyUser;
import com.authservice.model.VerificationToken;
import com.authservice.service.VerificationTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.authservice.service.MyUserService;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.sql.Timestamp;

@RestController
@RequestMapping("")
public class AuthController {

    @Autowired
    private MyUserService myUserService;
    @Autowired
    private VerificationTokenService verificationTokenService;

    @GetMapping(value = "/findAll", produces = "application/json; charset=utf-8")
    public ResponseEntity<MyUserDTO> findAll() {

        return new ResponseEntity(this.myUserService.findAll(), HttpStatus.OK);
    }

    @PostMapping(value = "/login", produces = "application/json; charset=utf-8")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest authRequest) {

        AuthResponse authResponse = this.myUserService.login(authRequest);
        if(authResponse.getAccessToken().equals("")){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(authResponse,HttpStatus.OK);
    }

    @PostMapping(value = "/register")
    public ResponseEntity<MyUserDTO> register(@RequestBody MyUserDTO myUserDTO) {
        MyUserDTO myUserDTO1 = this.myUserService.register(myUserDTO);
        if (myUserDTO.getUsername().equals("")){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(myUserDTO1, HttpStatus.CREATED);
    }

    @PostMapping("/email/verification")
    public ResponseEntity indentifyUser(@RequestBody ActivationDTO activationDTO) throws UserPrincipalNotFoundException {
        MyUserDTO user = this.myUserService.findMyUserByUsername(activationDTO.getUsername());
        if(user == null){
            throw new UserPrincipalNotFoundException("User not found");
        }

        // proveravamo da nije istekao expiru date
        VerificationToken verificationToken = this.verificationTokenService.findByToken(activationDTO.getToken());

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        if(verificationToken != null) {
            if (verificationToken.getExpiryDate().after(timestamp)) {
                this.myUserService.activateUser(user.getUsername());
                return new ResponseEntity<>(HttpStatus.OK);
            }
        }


        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
