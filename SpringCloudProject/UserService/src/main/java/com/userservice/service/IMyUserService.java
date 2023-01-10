package com.userservice.service;

import com.userservice.dto.MyUserDTO;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.List;

public interface IMyUserService {

    MyUserDTO findByUsername(String username);

    List<MyUserDTO> findAll();

    List<MyUserDTO> findAllRegistrated();

    boolean addMyUser(MyUserDTO dto);

    void activateUser(String username) throws UserPrincipalNotFoundException;

}
