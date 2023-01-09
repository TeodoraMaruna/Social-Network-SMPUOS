package com.postservice.service;

import com.postservice.dto.CommentDto;
import com.postservice.model.Comments;
import com.postservice.model.Post;
import com.postservice.repository.PostRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService implements ICommentService{

    @Autowired
    private PostRepository postRepository;

    @Override
    public void addComment(CommentDto commentDto, String postId) {
        ModelMapper modelMapper =  new ModelMapper();
        Post post = this.postRepository.findById(postId).get();
        post.addComment(modelMapper.map(commentDto, Comments.class));
        this.postRepository.save(post);
    }

    @Override
    public void deleteComment(Integer commentId, String postId) {
        Post post = this.postRepository.findById(postId).get();
        post.deleteComment(commentId);
        this.postRepository.save(post);
    }


}
