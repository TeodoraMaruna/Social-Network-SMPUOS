package com.authservice.controller;

import com.authservice.dto.MyUserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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

    @GetMapping(value = "", produces = "application/json; charset=utf-8")
    public ResponseEntity<?> info() {

        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
