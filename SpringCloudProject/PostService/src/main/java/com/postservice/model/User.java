package com.postservice.model;

public class User {

    private Integer userId;
    private Boolean status;

    private String username;

    public User(Integer userId, Boolean status) {
        this.userId = userId;
        this.status = status;
    }

    public User(Integer userId, Boolean status, String username) {
        this.userId = userId;
        this.status = status;
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

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
