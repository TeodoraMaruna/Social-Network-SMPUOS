package com.postservice.dto;

public class UserDto {

    private String username;
    private Boolean isPublic;

    public UserDto(String username, Boolean status) {
        this.username = username;
        this.isPublic = status;
    }

    public UserDto() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Boolean getPublic() {
        return isPublic;
    }

    public void setPublic(Boolean aPublic) {
        isPublic = aPublic;
    }
}
