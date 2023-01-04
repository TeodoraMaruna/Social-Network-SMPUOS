package com.authservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.authservice.dto.MyUserDTO;
import com.authservice.model.MyUser;
import com.authservice.repository.MyUserRepository;

import java.util.ArrayList;
import java.util.List;
@Service
public class MyUserService implements IMyUserService {
	@Autowired
    private MyUserRepository myUserRepository;
    @Autowired
    public MyUserService(MyUserRepository myUserRepository){
        this.myUserRepository = myUserRepository;
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

}
