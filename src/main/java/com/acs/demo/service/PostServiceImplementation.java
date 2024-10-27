package com.acs.demo.service;

import com.acs.demo.models.Post;
import com.acs.demo.models.User;
import com.acs.demo.repository.PostRepository;
import com.acs.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class PostServiceImplementation implements PostService{

    @Autowired
    PostRepository postRepository;
    @Autowired
    UserService userService;
    @Autowired
    UserRepository userRepository;

    @Override
    public Post createNewPost(Post post, Integer userId) throws Exception {

        User user = userService.findUserById(userId);

        Post newPost = new Post();
        newPost.setCaption(post.getCaption());
        newPost.setUser(user);
        newPost.setImageUrl(post.getImageUrl());
        newPost.setVideoUrl(post.getVideoUrl());
        newPost.setCreatedAt(LocalDateTime.now());

        postRepository.save(newPost);
        return newPost;
    }

    @Override
    public String deletePost(Integer postId, Integer userId) throws Exception {
        Post tmpPost = findPostByPostId(postId);
        User tmpUser = userService.findUserById(userId);

        if(!Objects.equals(tmpPost.getUser().getiUserId(), tmpUser.getiUserId())){
            throw new Exception("No delete access to this user");
        }

        postRepository.delete(tmpPost);
        return "Post with Id : "+postId+" deleted successfully";
    }

    @Override
    public List<Post> findPostByUserId(Integer userId) throws Exception {
        return postRepository.findPostByUserId(userId);
    }

    @Override
    public Post findPostByPostId(Integer postId) throws Exception {
        Optional<Post> tmpPost = postRepository.findById(postId);

        if(tmpPost.isEmpty()){
            throw new Exception("Post Not found with Id :" + postId);
        }

        return tmpPost.get();
    }

    @Override
    public List<Post> findAllPost() {
        return postRepository.findAll();
    }

    @Override
    public Post savedPost(Integer postId, Integer userId) throws Exception {
        Post tmpPost = findPostByPostId(postId);
        User tmpUser = userService.findUserById(userId);

        if(tmpUser.getSavedPosts().contains(tmpPost)){
            tmpUser.getSavedPosts().remove(tmpPost);
        }else {
            tmpUser.getSavedPosts().add(tmpPost);
        }
        userRepository.save(tmpUser);
        return tmpPost;
    }

    @Override
    public Post likePost(Integer postId, Integer userId) throws Exception {
        Post tmpPost = findPostByPostId(postId);
        User tmpUser = userService.findUserById(userId);

        if(tmpPost.getLikes().contains(tmpUser)){
            tmpPost.getLikes().remove(tmpUser);
        }else {
            tmpPost.getLikes().add(tmpUser);
        }
        return postRepository.save(tmpPost);
    }
}
