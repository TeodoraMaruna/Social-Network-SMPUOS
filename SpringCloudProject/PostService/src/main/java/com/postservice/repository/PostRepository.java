package com.postservice.repository;

import com.postservice.model.Post;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends MongoRepository<Post, String> {


    List<Post> findByUser_Username(String username);
}
