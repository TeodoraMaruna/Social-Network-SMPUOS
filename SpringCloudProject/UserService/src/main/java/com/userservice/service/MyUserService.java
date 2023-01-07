package com.userservice.service;

import com.userservice.dto.MyUserDTO;
import com.userservice.model.MyUser;
import com.userservice.repository.MyUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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
        Boolean uniqueUsername = checkIfUsernameUnique(dto.getUsername());
        boolean uniqueEmail = checkIfEmailUnique(dto);
        boolean uniquePhoneNumber = checkIfPhoneNumberUnique(dto);

        if (uniqueUsername && uniqueEmail && uniquePhoneNumber) {
            this.myUserRepository.save(dtoToModel(dto));
            return true;
        }
        return false;
    }

    public boolean editMyUser(MyUserDTO dto){
        MyUser user = this.myUserRepository.findMyUserByUsername(dto.getUsername());
        if (user != null) {
            // username se moze menjati
            user.setIsPublic(dto.getIsPublic());
            user.setFirstName(dto.getFirstName());
            user.setLastName(dto.getLastName());
            user.setEmail(dto.getEmail());
            user.setGender(dto.getGender());
            user.setPhoneNumber(dto.getPhoneNumber());

            boolean uniqueEmail = checkIfEmailUnique(dto);
            boolean uniquePhoneNumber = checkIfPhoneNumberUnique(dto);

            if (uniqueEmail && uniquePhoneNumber) {
                this.myUserRepository.save(user);
                return true;
            }
        }
        return false;
    }

    public boolean checkIfUsernameUnique(String username){
        for (MyUser user: this.myUserRepository.findAll()){
            if (user.getUsername().equals(username)){
                return false;
            }
        }
        return true;
    }

    public boolean checkIfEmailUnique(MyUserDTO dto){
        for (MyUser user: this.myUserRepository.findAll()){
            if (user.getEmail().equals(dto.getEmail()) && !user.getUsername().equals(dto.getUsername())){
                return false;
            }
        }
        return true;
    }

    public boolean checkIfPhoneNumberUnique(MyUserDTO dto){
        for (MyUser user: this.myUserRepository.findAll()){
            if (user.getPhoneNumber().equals(dto.getPhoneNumber()) && !user.getUsername().equals(dto.getUsername())){
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
        user.setGender(dto.getGender());
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
        dto.setGender(user.getGender());
        dto.setEmail(user.getEmail());
        dto.setPhoneNumber(user.getPhoneNumber());
        return dto;
    }

    @Transactional
    public boolean deleteByUsername(String username){
        MyUser myUser = this.myUserRepository.findMyUserByUsername(username);
        if (myUser == null){
            return false;
        }
        this.myUserRepository.deleteByUsername(username);
        return true;
    }
}
