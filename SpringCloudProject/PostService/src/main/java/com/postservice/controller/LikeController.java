package com.postservice.controller;

import com.postservice.dto.CommentDto;
import com.postservice.dto.LikeDto;
import com.postservice.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("")
public class LikeController {

    @Autowired
    private LikeService likeService;

    @RequestMapping(value = "/add-like/{postId}", method = RequestMethod.POST)
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity addLike(@RequestBody LikeDto likeDto, @PathVariable("postId") String postId)  {

        this.likeService.addLike(likeDto, postId);
        return new ResponseEntity(HttpStatus.CREATED);
    }
}
