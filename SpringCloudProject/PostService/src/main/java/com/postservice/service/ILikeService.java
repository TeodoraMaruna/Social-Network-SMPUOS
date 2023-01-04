package com.postservice.service;

import com.postservice.dto.LikeDto;

public interface ILikeService {

    void addLike (LikeDto likeDto, String postId);
}
