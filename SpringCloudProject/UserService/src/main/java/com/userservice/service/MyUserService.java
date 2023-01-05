package com.userservice.service;

import com.userservice.dto.MyUserDTO;
import com.userservice.model.MyUser;
import com.userservice.repository.MyUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MyUserService implements IMyUserService{

    @Autowired
    private MyUserRepository myUserRepository;

    @Autowired
    public MyUserService(MyUserRepository myUserRepository){
        this.myUserRepository = myUserRepository;
    }

    @Override
    public MyUserDTO findByUsername(String username) {
        MyUser user = this.myUserRepository.findMyUserByUsername(username);
        if (user != null) {
            return modelToDto(user);
        }
        return null;
    }

    @Override
    public List<MyUserDTO> findAll() {
        List<MyUserDTO> dtos = new ArrayList<>();
        for(MyUser user: this.myUserRepository.findAll()){
            dtos.add(modelToDto(user));
        }
        return dtos;
    }

    @Override
    public boolean addMyUser(MyUserDTO dto){
        Boolean unique = checkIfUsernameUnique(dto.getUsername());
        if (unique) {
            this.myUserRepository.save(dtoToModel(dto));
            return true;
        }
        return false;
    }

    @Autowired
    public boolean checkIfUsernameUnique(String username){
        for (MyUser user: this.myUserRepository.findAll()){
            if (user.getUsername().equals(username)){
                return false;
            }
        }
        return true;
    }

    private MyUser dtoToModel(MyUserDTO dto){
        MyUser user = new MyUser();
        user.setUsername(dto.getUsername());
//        user.setPassword(dto.getPassword());
        user.setRole(dto.getRole());
        user.setIsPublic(dto.getIsPublic());
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setEmail(dto.getEmail());
        user.setPhoneNumber(dto.getPhoneNumber());
        return user;
    }

    private MyUserDTO modelToDto(MyUser user){
        MyUserDTO dto = new MyUserDTO();
        dto.setUsername(user.getUsername());
//        dto.setPassword(user.getPassword());
        dto.setRole(user.getRole());
        dto.setIsPublic(user.getIsPublic());
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        dto.setEmail(user.getEmail());
        dto.setPhoneNumber(user.getPhoneNumber());
        return dto;
    }
}
