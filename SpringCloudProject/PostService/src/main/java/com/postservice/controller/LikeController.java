package com.postservice.controller;

import com.postservice.dto.CommentDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("")
public class LikeController {

    @RequestMapping(value = "/add-comment/{postId}", method = RequestMethod.POST)
    public ResponseEntity addPost(@RequestBody CommentDto commentDto, @PathVariable("postId") String postId)  {

        this.commentService.addComment(commentDto, postId);
        return new ResponseEntity(HttpStatus.CREATED);
    }
}
