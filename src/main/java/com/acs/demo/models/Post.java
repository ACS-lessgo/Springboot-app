package com.acs.demo.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Post {

    public Post(){

    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String caption;

    private String imageUrl;

    private String videoUrl;

    // Rel mapping for user to post
    // user creates many posts so rel mapping as many to one
    // user has multiple posts

    @JsonIgnore
    @ManyToOne
    private User user;

    private LocalDateTime createdAt;

    // user can like only once
    // one post can have many likes from many users
    // mapping as one to many

    @OneToMany
    private List<User> likes = new ArrayList<>();

    public Post(Integer id, String caption, String imageUrl, String videoUrl, User user, LocalDateTime createdAt, List<User> likes) {
        this.id = id;
        this.caption = caption;
        this.imageUrl = imageUrl;
        this.videoUrl = videoUrl;
        this.user = user;
        this.createdAt = createdAt;
        this.likes = likes;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public List<User> getLikes() {
        return likes;
    }

    public void setLikes(List<User> likes) {
        this.likes = likes;
    }
}
