package com.postservice.service;

import com.postservice.dto.LikeDto;
import com.postservice.model.Like;
import com.postservice.model.Post;
import com.postservice.repository.PostRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class LikeService implements  ILikeService{

    @Autowired
    private PostRepository postRepository;

    @Override
    public void addLike(LikeDto likeDto, String postId) {
        ModelMapper modelMapper = new ModelMapper();
        Post post = this.postRepository.findById(postId);
        post.addLike(modelMapper.map(likeDto, Like.class));
    }
}
