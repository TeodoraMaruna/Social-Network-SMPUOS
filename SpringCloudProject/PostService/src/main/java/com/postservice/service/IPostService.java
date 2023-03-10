package com.postservice.service;

import com.postservice.dto.PostDto;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;


public interface IPostService {

    PostDto insertPost(PostDto postDto) throws IOException, URISyntaxException;

    List<PostDto> findAllPostForUsername(String username);

    List<PostDto> findAll();

    PostDto findById(String id);

    String findImageLocationByImageId(String id);

    void deleteById(String postId);
}
