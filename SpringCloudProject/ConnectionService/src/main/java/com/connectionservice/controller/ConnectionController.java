package com.connectionservice.controller;

import com.connectionservice.dto.CreateConnectionDTO;
import com.connectionservice.dto.UserConnectionDTO;
import com.connectionservice.service.ConnectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    // reject

    @GetMapping(value = "/followers/{username}", produces = "application/json; charset=utf-8")
    public ResponseEntity<List<UserConnectionDTO>> findFollowersForUser(@PathVariable String username) {

        return new ResponseEntity(connectionService.findFollowersForUser(username), HttpStatus.OK);
    }

    @GetMapping(value = "/followRequests/{username}", produces = "application/json; charset=utf-8")
    public ResponseEntity<List<UserConnectionDTO>> findFollowRequestsForUser(@PathVariable String username) {

        return new ResponseEntity(connectionService.findFollowRequestsForUser(username), HttpStatus.OK);
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

}
