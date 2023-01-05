package com.userservice.service;

import com.userservice.dto.MyUserDTO;

import java.util.List;

public interface IMyUserService {

    MyUserDTO findByUsername(String username);

    List<MyUserDTO> findAll();

    boolean addMyUser(MyUserDTO dto);

    boolean checkIfUsernameUnique(String username);

}
