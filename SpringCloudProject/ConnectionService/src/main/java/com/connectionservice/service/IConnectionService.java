package com.connectionservice.service;

import com.connectionservice.dto.CreateConnectionDTO;
import com.connectionservice.dto.UserConnectionDTO;

import java.util.List;

public interface IConnectionService {

    List<UserConnectionDTO> findAll();

    UserConnectionDTO findByUsername(String username);

    void registerUserConnection(UserConnectionDTO dto);

    boolean createConnectionType(CreateConnectionDTO dto);

    boolean approveFollowRequest(CreateConnectionDTO dto);

    // reject

    boolean checkIfUsersExists(CreateConnectionDTO dto);

    boolean checkIfDifferentUsers(CreateConnectionDTO dto);

    List<UserConnectionDTO> findFollowersForUser(String username);

    List<UserConnectionDTO> findFollowRequestsForUser(String username);

    List<UserConnectionDTO> findBlockedUsersForUser(String username);

    List<UserConnectionDTO> findBlockedByUsersForUser(String username);

    boolean blockUser(CreateConnectionDTO dto);
}
