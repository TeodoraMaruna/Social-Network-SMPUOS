package com.userservice.controller;

import com.userservice.dto.MyUserDTO;
import com.userservice.service.MyUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.file.attribute.UserPrincipalNotFoundException;
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

    @GetMapping(value = "/findAllRegistrated", produces = "application/json; charset=utf-8")
    public ResponseEntity<List<MyUserDTO>> findAllRegistrated() {

        return new ResponseEntity<>(this.myUserService.findAllRegistrated(), HttpStatus.OK);
    }

    @GetMapping(value = "/findAll", produces = "application/json; charset=utf-8")
    public ResponseEntity<List<MyUserDTO>> findAll() {

        return new ResponseEntity<>(this.myUserService.findAll(), HttpStatus.OK);
    }

    @PostMapping(value = "/add", produces = "application/json; charset=utf-8")
    public ResponseEntity<MyUserDTO> addMyUser(@RequestBody MyUserDTO dto) {

        Boolean result = this.myUserService.addMyUser(dto);
        if (result) {
            dto.setSagaStatus("USER_SERVICE_CREATED");
            return new ResponseEntity<>(dto,HttpStatus.CREATED);
        }
        dto.setSagaStatus("CONNECTION_SERVICE_ROLLBACK");
        return new ResponseEntity<>(dto, HttpStatus.CONFLICT);
    }

    @PutMapping(value = "/edit", produces = "application/json; charset=utf-8")
    public ResponseEntity<?> editUser(@RequestBody MyUserDTO dto) {

        Boolean result = this.myUserService.editMyUser(dto);

        if (result) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.CONFLICT);
    }

    @PutMapping(value = "/editRegisterStatus/{username}", produces = "application/json; charset=utf-8")
    public ResponseEntity<?> editUserRegisterStatus(@PathVariable String username) throws UserPrincipalNotFoundException {

        try{
             this.myUserService.activateUser(username);
             return new ResponseEntity<>(HttpStatus.OK);
         }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "/check", produces = "application/json; charset=utf-8")
    public ResponseEntity<Boolean> checkEmailAndNumber(@RequestBody MyUserDTO dto) {

        Boolean emailUnique = this.myUserService.checkIfEmailUnique(dto);
        Boolean numberUnique = this.myUserService.checkIfPhoneNumberUnique(dto);
        if (emailUnique && numberUnique) {
            return new ResponseEntity<>(true,HttpStatus.OK);
        }
        return new ResponseEntity<>(false, HttpStatus.OK);
    }

}
