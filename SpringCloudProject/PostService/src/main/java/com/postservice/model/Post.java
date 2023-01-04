package com.postservice.model;

import org.bson.codecs.pojo.annotations.BsonId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;
import java.util.NoSuchElementException;

@Document
public class Post {


    @Id
    private String id;

    @Field("user")
    private User user;

    @Field("description")
    private String description;

    @Field("image")
    private Image image;

    @Field("comments")
    private List<Comments> comments;

    @Field("likes")
    private List<Like> likes;

    @Field("numberOfLikes")
    private Integer numberOfLikes;

    @Field("numberOfUnLikes")
    private Integer numberOfUnLikes;


    public Post(String id, User user, String description, Image image, List<Comments> comments, List<Like> likes, Integer numberOfLikes, Integer numberOfUnLikes) {
        this.id = id;
        this.user = user;
        this.description = description;
        this.image = image;
        this.comments = comments;
        this.likes = likes;
        this.numberOfLikes = numberOfLikes;
        this.numberOfUnLikes = numberOfUnLikes;
    }

    public Post() {
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public List<Comments> getComments() {
        return comments;
    }

    public void setComments(List<Comments> comments) {
        this.comments = comments;
    }

    public List<Like> getLikes() {
        return likes;
    }

    public void setLikes(List<Like> likes) {
        this.likes = likes;
    }

    public Integer getNumberOfLikes() {
        return numberOfLikes;
    }

    public void setNumberOfLikes(Integer numberOfLikes) {
        this.numberOfLikes = numberOfLikes;
    }



    public void addComment(Comments comments){
        if(this.getComments().size() == 0){
            comments.setId(1);
        }else{
            Integer max = this.getComments()
                    .stream()
                    .mapToInt(v -> v.getId())
                    .max().orElseThrow(NoSuchElementException::new);
            comments.setId(max+1);
        }

        this.getComments().add(comments);
    }

    public void deleteComment(Integer id){
        for(Comments c: this.getComments()){
            if(c.getId() == id){
                this.getComments().remove(c);
                break;
            }
        }
    }

    public Boolean addLike(Like l){
        for (Like l1: this.getLikes()){
            if(l1.getUsername().equals(l.getUsername())){
                return false;
            }
        }
        this.getLikes().add(l);
        this.setNumberOfLikes(this.numberOfLikes +1);
        return true;
    }


    public Boolean checkIfUserAlreadyLiked(String username){
        for (Like l1: this.getLikes()){
            if(l1.getUsername().equals(username)){
                return true;
            }
        }
        return false;
    }

}
