package com.acs.demo.service;

import com.acs.demo.models.Post;
import jakarta.persistence.criteria.CriteriaBuilder;

import java.util.List;

public interface PostService {
    public Post createNewPost(Post post , Integer userId) throws Exception;

    public String deletePost(Integer postId,Integer userId) throws Exception;

    public List<Post> findPostByUserId(Integer userId) throws Exception;

    public Post findPostByPostId(Integer postId) throws Exception;

    public List<Post> findAllPost();

    public Post savedPost(Integer postId ,Integer userId) throws Exception;

    public Post likePost(Integer postId ,Integer userId) throws Exception;
}
