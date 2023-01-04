package com.connectionservice.service;

import com.connectionservice.dto.CreateConnectionDTO;
import com.connectionservice.dto.UserConnectionDTO;
import com.connectionservice.model.UserConnection;
import com.connectionservice.repository.ConnectionRepository;

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

    @Override
    public void registerUserConnection(UserConnectionDTO dto){
        // TODO: check if username unique
        this.connectionRepository.save(new UserConnection(dto.getUsername(), dto.isPublic()));
    }

    @Override
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

    @Override
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

    @Override
    public boolean rejectFollowRequest(CreateConnectionDTO dto) {
        UserConnection receiver = this.connectionRepository.findByUsername(dto.getReceiverUsername());
        UserConnection sender = this.connectionRepository.findByUsername(dto.getSenderUsername());
        boolean exception = checkIfUsersExists(dto);
        if (!exception){
            return false;
        }

        // da li se sender nalazi u listi followerRequests od receiver-a
        // ako da, izbaciti tu vezu (followRequest)
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
        this.connectionRepository.removeFollowRequest(dto.getSenderUsername(), dto.getReceiverUsername());

        return true;
    }

    @Override
    public boolean checkIfUsersExists(CreateConnectionDTO dto){
        UserConnection receiver = this.connectionRepository.findByUsername(dto.getReceiverUsername());
        if (receiver == null){
            return false;
        }

        UserConnection sender = this.connectionRepository.findByUsername(dto.getSenderUsername());
        if (sender == null){
            return false;
        }

        return checkIfDifferentUsers(dto);
    }

    @Override
    public boolean checkIfDifferentUsers(CreateConnectionDTO dto){
        UserConnection receiver = this.connectionRepository.findByUsername(dto.getReceiverUsername());
        UserConnection sender = this.connectionRepository.findByUsername(dto.getSenderUsername());
        if (sender != null && receiver != null){
            if (sender.getUsername().equals(receiver.getUsername())){
                return false;
            }
            return true;
        }

        return false;
    }

    @Override
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

    @Override
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

    @Override
    public List<UserConnectionDTO> findBlockedUsersForUser(String username) {
        UserConnection user = this.connectionRepository.findByUsername(username);
        if (user == null){
            return null;
        }

        List<UserConnection> blocked = user.getBlocked();
        List<UserConnectionDTO> dtos = new ArrayList<>();
        for (UserConnection userConnection: blocked){
            dtos.add(new UserConnectionDTO(userConnection.getUsername(), userConnection.isPublic()));
        }
        return dtos;
    }

    @Override
    public List<UserConnectionDTO> findBlockedByUsersForUser(String username) {
        UserConnection user = this.connectionRepository.findByUsername(username);
        if (user == null){
            return null;
        }

        List<UserConnection> blockedBy = user.getBlockedBy();
        List<UserConnectionDTO> dtos = new ArrayList<>();
        for (UserConnection userConnection: blockedBy){
            dtos.add(new UserConnectionDTO(userConnection.getUsername(), userConnection.isPublic()));
        }
        return dtos;
    }

    @Override
    public boolean blockUser(CreateConnectionDTO dto){
        // dodavanje block veze od sender-a, ka receiver-u + dodavanje blockedBy veze od receiver-a ka sender-u
        UserConnection receiver = this.connectionRepository.findByUsername(dto.getReceiverUsername());
        UserConnection sender = this.connectionRepository.findByUsername(dto.getSenderUsername());
        boolean exception = checkIfUsersExists(dto);
        if (!exception){
            return false;
        }

        sender.blockUser(receiver);
        receiver.blockedByUser(sender);
        this.connectionRepository.save(sender);
        this.connectionRepository.save(receiver);   // zbog ovog save, prvo radimo dodavanje blocked veza, pa tek onda raskidanje starih veza

        // ako su pratioci, detach veze sa obe strane
        this.connectionRepository.removeFollower(dto.getSenderUsername(), dto.getReceiverUsername());
        this.connectionRepository.removeFollower(dto.getReceiverUsername(), dto.getSenderUsername());

        // ako postoji follow request sa jedne strane, detach te veze
        this.connectionRepository.removeFollowRequest(dto.getSenderUsername(), dto.getReceiverUsername());
        this.connectionRepository.removeFollowRequest(dto.getReceiverUsername(), dto.getSenderUsername());

        return true;
    }

    @Override
    public List<UserConnectionDTO> findRecommendedUsers(String username) {
        UserConnection user = this.connectionRepository.findByUsername(username);
        List<UserConnectionDTO> connections = new ArrayList<>();
        if (user != null) {

            for (UserConnection connection : user.getFollowers()) {
                for (UserConnection c : connection.getFollowers()) {
                    if (!connections.contains(c)
                            && !user.getFollowers().contains(c)
                            && !user.getUsername().equals(c.getUsername())
                            && !user.getBlocked().contains(c) && !user.getBlockedBy().contains(c)) {
                        connections.add(new UserConnectionDTO(c.getUsername(), c.isPublic()));
                    }
                }
            }
        }
        return connections;
    }
}
