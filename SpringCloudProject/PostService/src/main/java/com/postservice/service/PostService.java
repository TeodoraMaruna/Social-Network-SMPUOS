package com.postservice.service;

import com.postservice.dto.PostDto;
import com.postservice.model.Comments;
import com.postservice.model.Image;
import com.postservice.model.Post;
import com.postservice.model.User;
import com.postservice.repository.PostRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;

@Service
public class PostService implements IPostService{

    @Autowired
    private PostRepository postRepository;

    @Override
    public PostDto insertPost(PostDto postDto) throws IOException, URISyntaxException {

        Post post = this.postRepository.insert(dtoToModel(postDto));
        String path = uploadPostImage(post.getId(),postDto.getImageBase64());
        String path1 = "http://localhost:9000/post-service/image/" + post.getId();
        Image image = new Image();
        image.setImageLocation(path);
        image.setImageUrl(path1);
        post.setImage(image);
        post = this.postRepository.save(post);
        return modelToDto(post);
    }

    @Override
    public List<PostDto> findAllPostForUserId(Integer userId) {
        List<Post> posts = this.postRepository.findByUser_UserId(userId);
        List<PostDto> postDtos = new ArrayList<>();
        for(Post p: posts){
            PostDto postDto = modelToDto(p);
            postDtos.add(postDto);
        }
        return postDtos;
    }

    @Override
    public List<PostDto> findAll() {
        List<PostDto> dtos = new ArrayList<>();
        for (Post p: this.postRepository.findAll()){
            dtos.add(modelToDto(p));
        }
        return dtos;
    }

    @Override
    public PostDto findById(String id) {
        Post post = this.postRepository.findById(id);
        return modelToDto(post);
    }

    private Post dtoToModel(PostDto postDto){
        Post post = new Post();
        post.setDescription(postDto.getDescription());
        post.setNumberOfLikes(0);
        post.setComments(new ArrayList<>());
        User user = new User();
        user.setUserId(postDto.getUser().getUserId());
        user.setPublic(postDto.getUser().getPublic());
        post.setUser(user);
        post.setLikes(new ArrayList<>());
        return post;
    }

    private PostDto modelToDto(Post post){
        ModelMapper modelMapper = new ModelMapper();
        PostDto postDto = modelMapper.map(post, PostDto.class);
        postDto.setImageUrl(post.getImage().getImageUrl());
        return postDto;
    }

    private String uploadPostImage(String id, String newImage) throws IOException, URISyntaxException {
        String res = "";
        if (newImage != null) {
            if (!newImage.isEmpty() && newImage.startsWith("data:image")) {
                String date = new Date().toString();
                date = date.replaceAll("\\s", "");
                date = date.replaceAll("\\:", "");
                String path = "/post" + id + date +".jpg";
                res = Base64DecodeAndSave(newImage, path);
            }
        }
        return res;
    }

    public String Base64DecodeAndSave(String base64String, String imagePath) throws FileNotFoundException, IOException, URISyntaxException {
        File myObj = new File("images");
        boolean bool = myObj.mkdir();
        String path1 = myObj.getPath();
        System.out.println(path1);
        String part[] = base64String.split(",");
        String path = path1 + "/" + imagePath;
        byte[] data = Base64.getDecoder().decode(part[1]);
        try (OutputStream stream = new FileOutputStream(path)) {
            stream.write(data);
        }
        return path;
    }

}
