package com.postservice.model;

public class User {

//    private Integer userId;
    private Boolean isPublic;
    private String username;

    public User(Boolean status, String username) {
        this.isPublic = status;
        this.username = username;
    }

    public User() {
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
