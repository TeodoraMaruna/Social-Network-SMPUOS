package com.connectionservice.controller;

import com.connectionservice.dto.CreateConnectionDTO;
import com.connectionservice.dto.MyUserDTO;
import com.connectionservice.dto.UserConnectionDTO;
import com.connectionservice.service.ConnectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("")
public class ConnectionController {

    @Autowired
    private ConnectionService connectionService;

    @GetMapping(value = "/findAll", produces = "application/json; charset=utf-8")
    public ResponseEntity<List<UserConnectionDTO>> findAll() {

    	return new ResponseEntity(connectionService.findAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/findByUsername/{username}", produces = "application/json; charset=utf-8")
    public ResponseEntity<UserConnectionDTO> findByUsername(@PathVariable String username) {

        return new ResponseEntity(connectionService.findByUsername(username), HttpStatus.OK);
    }

    @PostMapping(value= "/connection", produces = "application/json; charset=utf-8")
    public ResponseEntity<?> createConnection(@RequestBody CreateConnectionDTO connectionDTO) {

        boolean result = this.connectionService.createConnectionType(connectionDTO);
        if (result) {
            return new ResponseEntity(HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.CONFLICT);
   }

    @PostMapping(value= "/approveFollowRequest", produces = "application/json; charset=utf-8")
    public ResponseEntity<?> approveFollowRequest(@RequestBody CreateConnectionDTO connectionDTO) {

        boolean result = this.connectionService.approveFollowRequest(connectionDTO);
        if (result) {
            return new ResponseEntity(HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.CONFLICT);
    }

    @PostMapping(value= "/rejectFollowRequest", produces = "application/json; charset=utf-8")
    public ResponseEntity<?> rejectFollowRequest(@RequestBody CreateConnectionDTO connectionDTO) {

        boolean result = this.connectionService.rejectFollowRequest(connectionDTO);
        if (result) {
            return new ResponseEntity(HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.CONFLICT);
    }

    @GetMapping(value = "/followers/{username}", produces = "application/json; charset=utf-8")
    public ResponseEntity<List<UserConnectionDTO>> findFollowersForUser(@PathVariable String username) {

        return new ResponseEntity(connectionService.findFollowersForUser(username), HttpStatus.OK);
    }

    @GetMapping(value = "/followRequests/{username}", produces = "application/json; charset=utf-8")
    public ResponseEntity<List<UserConnectionDTO>> findFollowRequestsForUser(@PathVariable String username) {

        return new ResponseEntity(connectionService.findFollowRequestsForUser(username), HttpStatus.OK);
    }

    @GetMapping(value = "/sentFollowRequests/{username}", produces = "application/json; charset=utf-8")
    public ResponseEntity<List<UserConnectionDTO>> findSentFollowRequestsForUser(@PathVariable String username) {

        return new ResponseEntity(connectionService.findSentFollowRequestsForUser(username), HttpStatus.OK);
    }

    @GetMapping(value = "/findBlocked/{username}", produces = "application/json; charset=utf-8")
    public ResponseEntity<List<UserConnectionDTO>> findBlockedUsersForUser(@PathVariable String username) {

        return new ResponseEntity(connectionService.findBlockedUsersForUser(username), HttpStatus.OK);
    }

    @GetMapping(value = "/findBlockedBy/{username}", produces = "application/json; charset=utf-8")
    public ResponseEntity<List<UserConnectionDTO>> findBlockedByUsersForUser(@PathVariable String username) {

        return new ResponseEntity(connectionService.findBlockedByUsersForUser(username), HttpStatus.OK);
    }

    @PostMapping(value= "/registerUserConnection", produces = "application/json; charset=utf-8")
    public ResponseEntity<?> registerUserConnection(@RequestBody UserConnectionDTO userConnectionDTO) {

        this.connectionService.registerUserConnection(userConnectionDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping(value= "/blockUser", produces = "application/json; charset=utf-8")
    public ResponseEntity<?> blockUser(@RequestBody CreateConnectionDTO connectionDTO) {

        boolean result = this.connectionService.blockUser(connectionDTO);
        if (result) {
            return new ResponseEntity(HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.CONFLICT);
    }

    @DeleteMapping(value= "/removeFollower", produces = "application/json; charset=utf-8")
    public ResponseEntity<?> removeFollower(@RequestBody CreateConnectionDTO connectionDTO) {

        this.connectionService.removeFollower(connectionDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value= "/removeFollowRequest", produces = "application/json; charset=utf-8")
    public ResponseEntity<?> removeFollowRequest(@RequestBody CreateConnectionDTO connectionDTO) {

        this.connectionService.removeFollowRequest(connectionDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/findRecommended/{username}", produces = "application/json; charset=utf-8")
    public ResponseEntity<List<UserConnectionDTO>> findRecommendedUsers(@PathVariable String username) {

        return new ResponseEntity(connectionService.findRecommendedUsers(username), HttpStatus.OK);
    }

    @PutMapping(value= "/editUser", produces = "application/json; charset=utf-8")
    public ResponseEntity<?> editUser(@RequestBody UserConnectionDTO dto) {

        this.connectionService.editUser(dto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
