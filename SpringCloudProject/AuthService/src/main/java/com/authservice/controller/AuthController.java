package com.authservice.controller;

import com.authservice.dto.AuthRequest;
import com.authservice.dto.AuthResponse;
import com.authservice.dto.MyUserDTO;
import com.authservice.model.MyUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.authservice.service.MyUserService;

@RestController
@RequestMapping("")
public class AuthController {

    @Autowired
    private MyUserService myUserService;

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

}
