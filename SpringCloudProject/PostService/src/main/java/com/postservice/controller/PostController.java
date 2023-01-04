package com.postservice.controller;

import com.postservice.dto.PostDto;
import com.postservice.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("")
public class PostController {

    @Autowired
    private PostService postService;

    @RequestMapping(value = "", method = RequestMethod.GET,
            produces = "application/json; charset=utf-8")
    public ResponseEntity getInfo() {

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/add-post", method = RequestMethod.POST)
    public ResponseEntity<PostDto> addPost(@RequestBody PostDto postDto) throws IOException, URISyntaxException {

        return new ResponseEntity<>(this.postService.insertPost(postDto),HttpStatus.CREATED);
    }

    @RequestMapping(value = "/find-post/{userId}", method = RequestMethod.GET)
    public ResponseEntity<List<PostDto>> findPostForUser(@PathVariable("userId") Integer userId) {

        return new ResponseEntity<>(this.postService.findAllPostForUserId(userId),HttpStatus.FOUND);
    }
}

