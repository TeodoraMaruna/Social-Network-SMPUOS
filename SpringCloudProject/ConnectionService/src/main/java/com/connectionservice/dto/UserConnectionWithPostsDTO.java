package com.connectionservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserConnectionWithPostsDTO {

    // TODO: delete class
    private String username;
    private boolean isPublic;
    private List<PostDTO> posts;
}
