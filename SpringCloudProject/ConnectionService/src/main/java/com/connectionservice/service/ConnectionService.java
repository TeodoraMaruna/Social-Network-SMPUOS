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

    public UserConnectionDTO registerUserConnection(UserConnectionDTO dto){
        // TODO: check if username unique
        try {
            this.connectionRepository.save(new UserConnection(dto.getUsername(), dto.isPublic()));

        }catch (Exception e){
            dto.setSagaStatus("AUTH_SERVICE_ROLLBACK");
            return dto;
        }
        dto.setSagaStatus("CONNECTION_SERVICE_CREATED");
        return dto;
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
            this.connectionRepository.save(receiver);

            this.connectionRepository.removeFollowRequest(dto.getSenderUsername(), dto.getReceiverUsername());
            this.connectionRepository.removeFollowRequest(dto.getReceiverUsername(), dto.getSenderUsername());
        } else {
            // ako je sender private - provera da li je receiver vec poslao follow request senderu
            if (!sender.isPublic()){
                for(UserConnection s: sender.getFollowRequests()){
                    if (s.getUsername().equals(receiver.getUsername())){  // receiver je vec poslao follow request senderu
                        receiver.createFollower(sender);                  // sender i receiver postaju followers
                        sender.createFollower(receiver);
                        this.connectionRepository.save(sender);
                        this.connectionRepository.save(receiver);
                        this.connectionRepository.removeFollowRequest(dto.getReceiverUsername(), dto.getSenderUsername());
                        return true;
                    }
                }
            }

            // kreiranje zahteva za konekciju
            receiver.createFollowRequests(sender);
            this.connectionRepository.save(receiver);
        }
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
    public List<UserConnectionDTO> findSentFollowRequestsForUser(String username) {
        UserConnection user = this.connectionRepository.findByUsername(username);
        if (user == null){
            return null;
        }

        List<UserConnectionDTO> dtos = new ArrayList<>();
        for (UserConnection userConnection: this.connectionRepository.findAll()){
            List<UserConnection> followRequests = userConnection.getFollowRequests();
            for (UserConnection u: followRequests){
                if (u.getUsername().equals(username)){
                    dtos.add(new UserConnectionDTO(userConnection.getUsername(), userConnection.isPublic()));
                }
            }
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
                    if (!containsUserDTO(connections, c)
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

    @Override
    public UserConnectionDTO deleteUserConnection(String username) {
        try {
            this.connectionRepository.deleteByUsername(username);
            return new UserConnectionDTO(username,false, "AUTH_SERVICE_ROLLBACK");
        }catch (Exception e){
            return new UserConnectionDTO(username,false, "CONNECTION_SERVICE_ROLLBACK");
        }
    }
  
    public boolean containsUserDTO(List<UserConnectionDTO> connections, UserConnection c){
        for (UserConnectionDTO dto: connections){
            if (dto.getUsername().equals(c.getUsername())){
                return true;
            }
        }
        return false;
    }

    public boolean containsUser(List<UserConnection> connections, UserConnection c){
        for (UserConnection user: connections){
            if (user.getUsername().equals(c.getUsername())){
                return true;
            }
        }
        return false;
    }

    @Override
    public void editUser(UserConnectionDTO dto){
        UserConnection user = this.connectionRepository.findByUsername(dto.getUsername());
        user.setPublic(dto.isPublic());
        this.connectionRepository.save(user);

        // aproveAllFollowRequests
        if (dto.isPublic()){
            for(UserConnection connection: user.getFollowRequests()) {
                approveFollowRequest(new CreateConnectionDTO(connection.getUsername(), user.getUsername()));
            }
        }
    }

    @Override
    public void removeFollower(CreateConnectionDTO dto){
        this.connectionRepository.removeFollower(dto.getSenderUsername(), dto.getReceiverUsername());
    }

    @Override
    public void removeFollowRequest(CreateConnectionDTO dto) {
        this.connectionRepository.removeFollowRequest(dto.getSenderUsername(), dto.getReceiverUsername());
    }

    @Override
    public void removeBlocked(CreateConnectionDTO dto) {
        this.connectionRepository.removeBlocked(dto.getSenderUsername(), dto.getReceiverUsername());
    }

    @Override
    public void removeBlockedBy(CreateConnectionDTO dto) {
        this.connectionRepository.removeBlockedBy(dto.getSenderUsername(), dto.getReceiverUsername());
    }

    @Override
    public boolean checkIfUsersFollowEachOther(CreateConnectionDTO dto) {
        UserConnection receiver = this.connectionRepository.findByUsername(dto.getReceiverUsername());
        UserConnection sender = this.connectionRepository.findByUsername(dto.getSenderUsername());
        boolean exception = checkIfUsersExists(dto);
        if (!exception){
            return false;
        }

        for (UserConnection u: receiver.getFollowers()){
            if (u.getUsername().equals(sender.getUsername())){
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean checkIfUserSentFollowRequest(CreateConnectionDTO dto) {
        UserConnection receiver = this.connectionRepository.findByUsername(dto.getReceiverUsername());
        UserConnection sender = this.connectionRepository.findByUsername(dto.getSenderUsername());
        boolean exception = checkIfUsersExists(dto);
        if (!exception){
            return false;
        }

        for (UserConnection u: receiver.getFollowRequests()){
            if (u.getUsername().equals(sender.getUsername())){
                return true;
            }
        }
        return false;
    }

    @Override
    public List<UserConnectionDTO> allowedUserConnections(String username) {
        UserConnection user = this.connectionRepository.findByUsername(username);
        if (user == null){
            return null;
        }

        List<UserConnectionDTO> dtos = new ArrayList<>();
        List<UserConnection> blocked = user.getBlocked();
        List<UserConnection> blockedBy = user.getBlockedBy();

        for(UserConnection u: this.connectionRepository.findAll()) {
            if (!containsUser(blocked, u) && !containsUser(blockedBy, u) && !u.getUsername().equals(username)){
                dtos.add(new UserConnectionDTO(u.getUsername(), u.isPublic()));
            }
        }
        return dtos;
    }
  
}
