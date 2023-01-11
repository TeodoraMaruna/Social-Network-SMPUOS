package com.connectionservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserConnectionDTO {
	
    private String username;
    private boolean isPublic;
    private Boolean isPublicStatus;
    private String sagaStatus;

    public UserConnectionDTO(String username, Boolean isPublic) {
        this.username = username;
        this.isPublic = isPublic;
    }

    public UserConnectionDTO(String username, Boolean isPublic, String sagaStatus) {
        this.username = username;
        this.isPublic = isPublic;
        this.sagaStatus = sagaStatus;
    }
}
