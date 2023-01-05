package com.authservice.controller;

import com.authservice.dto.AuthRequest;
import com.authservice.dto.AuthResponse;
import com.authservice.dto.MyUserDTO;
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
        return new ResponseEntity<>(authResponse,HttpStatus.FOUND);
    }

    @PostMapping(value = "/register")
    public ResponseEntity<AuthRequest> register(@RequestBody AuthRequest authRequest) {
        return new ResponseEntity(this.myUserService.register(authRequest), HttpStatus.CREATED);
    }

}
