package com.postservice.controller;

import com.postservice.dto.CommentDto;
import com.postservice.dto.PostDto;
import com.postservice.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URISyntaxException;

@RestController
@RequestMapping("")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @RequestMapping(value = "/add-comment/{postId}", method = RequestMethod.POST)
    public ResponseEntity addComment(@RequestBody CommentDto commentDto, @PathVariable("postId") String postId)  {

        this.commentService.addComment(commentDto, postId);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @RequestMapping(value = "/delete-comment/{postId}/{commentId}", method = RequestMethod.DELETE)
    public ResponseEntity deleteComment(@PathVariable("commentId") Integer commentId,@PathVariable("postId") String postId)  {

        this.commentService.deleteComment(commentId, postId);
        return new ResponseEntity(HttpStatus.OK);
    }
}
