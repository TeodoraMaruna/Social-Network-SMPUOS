package com.postservice.service;

import com.postservice.dto.LikeDto;
import com.postservice.model.Like;
import com.postservice.model.Post;
import com.postservice.repository.PostRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LikeService implements ILikeService{

    @Autowired
    private PostRepository postRepository;

    @Override
    public void addLike(LikeDto likeDto, String postId) {
        ModelMapper modelMapper = new ModelMapper();
        Post post = this.postRepository.findById(postId);
        Like like = modelMapper.map(likeDto, Like.class);
        post.addLike(like);
        this.postRepository.save(post);
    }
}
