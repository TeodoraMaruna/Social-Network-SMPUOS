package com.connectionservice.service;

import com.connectionservice.dto.CreateConnectionDTO;
import com.connectionservice.dto.UserConnectionDTO;
import com.connectionservice.model.UserConnection;
import com.connectionservice.repository.ConnectionRepository;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ConnectionService implements IConnectionService {

    @Autowired
    private ConnectionRepository connectionRepository;

    @Autowired
    public ConnectionService(ConnectionRepository connectionRepository){
        this.connectionRepository = connectionRepository;
    }

    @Override
    public List<UserConnectionDTO> findAll() {
    	List<UserConnectionDTO> dtos = new ArrayList();
    	for(UserConnection conn: connectionRepository.findAll()) {
    		dtos.add(new UserConnectionDTO(conn.getUsername(), conn.isPublic()));
    	}
        return dtos;
    }

    @Override
    public UserConnectionDTO findByUsername(String username) {
        UserConnection conn = connectionRepository.findByUsername(username);
        return new UserConnectionDTO(conn.getUsername(), conn.isPublic());
    }

    public boolean createConnectionType(CreateConnectionDTO dto){
        UserConnection receiver = this.connectionRepository.findByUsername(dto.getReceiverUsername());
        UserConnection sender = this.connectionRepository.findByUsername(dto.getSenderUsername());
        boolean exception = checkIfUsersExists(dto);
        if (!exception){
            return false;
        }

        if (receiver.isPublic()){
            // kreiranje konekcije
            receiver.createFollower(sender);
            sender.createFollower(receiver);
            this.connectionRepository.save(sender);
        } else {
            // kreiranje zahteva za konekciju
            receiver.createFollowRequests(sender);
        }
        this.connectionRepository.save(receiver);
        return true;
    }

    public boolean approveFollowRequest(CreateConnectionDTO dto){
        UserConnection receiver = this.connectionRepository.findByUsername(dto.getReceiverUsername());
        UserConnection sender = this.connectionRepository.findByUsername(dto.getSenderUsername());
        boolean exception = checkIfUsersExists(dto);
        if (!exception){
            return false;
        }

        // da li se sender nalazi u listi followerRequests od receiver-a
        // ako da, izbaciti tu vezu (followRequest) + dodati veze sa obe strane za followers

        List<UserConnection> followRequests = receiver.getFollowRequests();
        boolean found = false;
        for(UserConnection user: followRequests){
            if (user.getUsername().equals(sender.getUsername())){
                found = true;
            }
        }

        if (!found){
            return false;
        }

        receiver.createFollower(sender);
        sender.createFollower(receiver);
        this.connectionRepository.save(receiver);
        this.connectionRepository.save(sender);

        this.connectionRepository.removeFollowRequest(dto.getSenderUsername(), dto.getReceiverUsername());

        return true;
    }

    public boolean checkIfUsersExists(CreateConnectionDTO dto){
        UserConnection receiver = this.connectionRepository.findByUsername(dto.getReceiverUsername());
        if (receiver == null){
            return false;
        }

        UserConnection sender = this.connectionRepository.findByUsername(dto.getSenderUsername());
        if (sender == null){
            return false;
        }

        return true;
    }

    public List<UserConnectionDTO> findFollowersForUser(String username){
        UserConnection user = this.connectionRepository.findByUsername(username);
        if (user == null){
            return null;
        }

        List<UserConnection> followers = user.getFollowers();
        List<UserConnectionDTO> dtos = new ArrayList<>();
        for (UserConnection userConnection: followers){
            dtos.add(new UserConnectionDTO(userConnection.getUsername(), userConnection.isPublic()));
        }
        return dtos;
    }

    public List<UserConnectionDTO> findFollowRequestsForUser(String username){
        UserConnection user = this.connectionRepository.findByUsername(username);
        if (user == null){
            return null;
        }

        List<UserConnection> followRequests = user.getFollowRequests();
        List<UserConnectionDTO> dtos = new ArrayList<>();
        for (UserConnection userConnection: followRequests){
            dtos.add(new UserConnectionDTO(userConnection.getUsername(), userConnection.isPublic()));
        }
        return dtos;
    }
}
