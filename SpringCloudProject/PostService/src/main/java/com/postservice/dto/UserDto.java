package com.postservice.dto;

public class UserDto {

    private Integer userId;
    private Boolean isPublic;

    public UserDto(Integer userId, Boolean status) {
        this.userId = userId;
        this.isPublic = status;
    }

    public UserDto() {
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Boolean getPublic() {
        return isPublic;
    }

    public void setPublic(Boolean aPublic) {
        isPublic = aPublic;
    }
}
