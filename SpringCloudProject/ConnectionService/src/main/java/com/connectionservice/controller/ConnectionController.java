package com.connectionservice.controller;

import com.connectionservice.dto.*;
import com.connectionservice.service.ConnectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
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

    @GetMapping(value = "/postsFromFollowers/{username}", produces = "application/json; charset=utf-8")
    public ResponseEntity<List<PostDTO>> findPostsFromFollowersForUser(@PathVariable String username) {

        RestTemplateBuilder restTemplateBuilder = new RestTemplateBuilder();
        RestTemplate restTemplate = restTemplateBuilder.build();
        List<PostDTO> allPosts = new ArrayList<>();

        // za svakog korisnika da vrati listu postova
        for(UserConnectionDTO dto: connectionService.findFollowersForUser(username)){
            ResponseEntity<PostDTO[]> response =
                    restTemplate.getForEntity(
                            "http://localhost:9000/post-service/find-all-posts/user/"
                                    + dto.getUsername(), PostDTO[].class);
            PostDTO[] posts = response.getBody();

            if (posts != null) {
                for (PostDTO p : posts) {
                    allPosts.add(p);
                }
            }
        }

        return new ResponseEntity(allPosts, HttpStatus.OK);
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

    @PostMapping(value= "/removeFollower", produces = "application/json; charset=utf-8")
    public ResponseEntity<?> removeFollower(@RequestBody CreateConnectionDTO connectionDTO) {

        this.connectionService.removeFollower(connectionDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(value= "/removeFollowRequest", produces = "application/json; charset=utf-8")
    public ResponseEntity<?> removeFollowRequest(@RequestBody CreateConnectionDTO connectionDTO) {

        this.connectionService.removeFollowRequest(connectionDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(value= "/removeBlocked", produces = "application/json; charset=utf-8")
    public ResponseEntity<?> removeBlocked(@RequestBody CreateConnectionDTO connectionDTO) {

        this.connectionService.removeBlocked(connectionDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(value= "/removeBlockedBy", produces = "application/json; charset=utf-8")
    public ResponseEntity<?> removeBlockedBy(@RequestBody CreateConnectionDTO connectionDTO) {

        this.connectionService.removeBlockedBy(connectionDTO);
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

    @PostMapping(value= "/checkIfUsersFollowEachOther", produces = "application/json; charset=utf-8")
    public ResponseEntity<Boolean> checkIfUsersFollowEachOther(@RequestBody CreateConnectionDTO dto) {

        return new ResponseEntity<>(this.connectionService.checkIfUsersFollowEachOther(dto), HttpStatus.OK);
    }

    @PostMapping(value= "/checkIfUserSentFollowRequest", produces = "application/json; charset=utf-8")
    public ResponseEntity<Boolean> checkIfUserSentFollowRequest(@RequestBody CreateConnectionDTO dto) {

        return new ResponseEntity<>(this.connectionService.checkIfUserSentFollowRequest(dto), HttpStatus.OK);
    }

    @GetMapping(value = "/allowedUserConnections/{username}", produces = "application/json; charset=utf-8")
    public ResponseEntity<List<MyUserDTO>> findAllowedUserConnections(@PathVariable String username) {

        RestTemplateBuilder restTemplateBuilder = new RestTemplateBuilder();
        RestTemplate restTemplate = restTemplateBuilder.build();
        List<MyUserDTO> myUserDTOS = new ArrayList<>();

        // za svakog korisnika da vrati listu postova
        for(UserConnectionDTO dto: connectionService.allowedUserConnections(username)){
            ResponseEntity<MyUserDTO> response =
                    restTemplate.getForEntity(
                            "http://localhost:9000/user-service/findByUsername/"
                                    + dto.getUsername(), MyUserDTO.class);
            MyUserDTO user = response.getBody();

            if (user != null) {
                myUserDTOS.add(user);
            }
        }

        return new ResponseEntity(myUserDTOS, HttpStatus.OK);
    }

}
