package com.postservice.model;

public class User {

    private Integer userId;
    private Boolean isPublic;
    private String username;

    public User(Integer userId, Boolean status) {
        this.userId = userId;
        this.isPublic = status;
    }

    public User(Integer userId, Boolean status, String username) {
        this.userId = userId;
        this.isPublic = status;
        this.username = username;
    }

    public User() {
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
