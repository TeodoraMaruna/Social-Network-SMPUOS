package com.postservice.service;

import com.postservice.dto.PostDto;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;


public interface IPostService {

    PostDto insertPost(PostDto postDto) throws IOException, URISyntaxException;

    List<PostDto> findAllPostForUserId(Integer userId);
}
