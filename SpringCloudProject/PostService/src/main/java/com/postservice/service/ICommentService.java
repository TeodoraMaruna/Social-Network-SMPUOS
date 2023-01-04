package com.postservice.service;

import com.postservice.dto.CommentDto;

public interface ICommentService {

    void addComment(CommentDto commentDto, String postId);
    void deleteComment(Integer commentId, String postId);
}
