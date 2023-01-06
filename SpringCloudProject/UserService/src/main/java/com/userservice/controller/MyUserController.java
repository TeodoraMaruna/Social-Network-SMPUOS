package com.userservice.controller;

import com.userservice.dto.MyUserDTO;
import com.userservice.dto.UserConnectionDTO;
import com.userservice.model.MyUser;
import com.userservice.service.MyUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("")
public class MyUserController {

    @Autowired
    private MyUserService myUserService;

    @GetMapping(value = "", produces = "application/json; charset=utf-8")
    public ResponseEntity getInfo() {

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "/findByUsername/{username}", produces = "application/json; charset=utf-8")
    public ResponseEntity<MyUserDTO> findByUsername(@PathVariable String username) {

        MyUserDTO dto = this.myUserService.findByUsername(username);
        if (dto == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @GetMapping(value = "/findAll", produces = "application/json; charset=utf-8")
    public ResponseEntity<List<MyUserDTO>> findAll() {

        return new ResponseEntity<>(this.myUserService.findAll(), HttpStatus.OK);
    }

    @PostMapping(value = "/add", produces = "application/json; charset=utf-8")
    public ResponseEntity<?> addMyUser(@RequestBody MyUserDTO dto) {

        Boolean result = this.myUserService.addMyUser(dto);
        if (result) {
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.CONFLICT);
    }

    @PutMapping(value = "/edit", produces = "application/json; charset=utf-8")
    public ResponseEntity<?> editUser(@RequestBody MyUserDTO dto) {

        Boolean result = this.myUserService.editMyUser(dto);
        if (result) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.CONFLICT);
    }

}
