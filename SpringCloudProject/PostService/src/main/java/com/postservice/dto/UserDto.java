package com.postservice.dto;

public class UserDto {

    private Integer userId;
    private Boolean status;

    public UserDto(Integer userId, Boolean status) {
        this.userId = userId;
        this.status = status;
    }

    public UserDto() {
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
