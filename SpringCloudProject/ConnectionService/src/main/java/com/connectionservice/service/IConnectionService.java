package com.connectionservice.service;

import com.connectionservice.dto.UserConnectionDTO;

import java.util.List;

public interface IConnectionService {

    List<UserConnectionDTO> findAll();

    UserConnectionDTO findByUsername(String username);
}
