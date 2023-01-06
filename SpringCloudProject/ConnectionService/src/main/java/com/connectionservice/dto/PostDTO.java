package com.connectionservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class PostDTO {
    private String Id;

    private String description;

    private List<CommentDTO> comments;

    private List<LikeDTO> likes;

    private UserConnectionDTO user;

    private String imageUrl;

    private String imageBase64;

    private Integer numberOfLikes;

}
