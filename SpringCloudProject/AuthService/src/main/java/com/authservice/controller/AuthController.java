package com.authservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("")
public class AuthController {

    @RequestMapping(value = "", method = RequestMethod.GET,
            produces = "application/json; charset=utf-8")
    public ResponseEntity getInfo() {

        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}