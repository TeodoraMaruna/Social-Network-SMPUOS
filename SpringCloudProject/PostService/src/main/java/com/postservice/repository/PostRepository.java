package com.postservice.repository;

import com.postservice.model.Post;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface PostRepository extends MongoRepository<Post, Integer> {

    Post findById(String string);
    List<Post> findByUser_UserId(Integer userId);
}
