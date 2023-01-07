package com.connectionservice.service;

import com.connectionservice.dto.CreateConnectionDTO;
import com.connectionservice.dto.UserConnectionDTO;

import java.util.List;

public interface IConnectionService {

    List<UserConnectionDTO> findAll();

    UserConnectionDTO findByUsername(String username);

    UserConnectionDTO registerUserConnection(UserConnectionDTO dto);

    boolean createConnectionType(CreateConnectionDTO dto);

    boolean approveFollowRequest(CreateConnectionDTO dto);

    boolean rejectFollowRequest(CreateConnectionDTO dto);

    boolean checkIfUsersExists(CreateConnectionDTO dto);

    boolean checkIfDifferentUsers(CreateConnectionDTO dto);

    List<UserConnectionDTO> findFollowersForUser(String username);

    List<UserConnectionDTO> findFollowRequestsForUser(String username);

    List<UserConnectionDTO> findBlockedUsersForUser(String username);

    List<UserConnectionDTO> findBlockedByUsersForUser(String username);

    boolean blockUser(CreateConnectionDTO dto);

    List<UserConnectionDTO> findRecommendedUsers(String username);

    UserConnectionDTO deleteUserConnection(String username);
}
