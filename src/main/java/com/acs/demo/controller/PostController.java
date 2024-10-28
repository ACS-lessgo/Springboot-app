package com.acs.demo.controller;

import com.acs.demo.models.Post;
import com.acs.demo.response.ApiResponse;
import com.acs.demo.service.PostService;
import com.acs.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PostController {

    @Autowired
    PostService postService;

    @Autowired
    UserService userService;

    @PostMapping("/posts/createPost")
    public ResponseEntity<Post> createPost(@RequestBody Post post,@RequestHeader("Authorization") String jwtToken) throws Exception {
        Integer userId = userService.getUserByJWT(jwtToken).getiUserId();
        Post newPost = postService.createNewPost(post,userId);
        return new ResponseEntity<>(newPost, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/posts/deletePost/{postId}")
    public ResponseEntity<ApiResponse> deletePost(@PathVariable("postId") Integer postId,@RequestHeader("Authorization") String jwtToken) throws Exception {
        Integer userId = userService.getUserByJWT(jwtToken).getiUserId();
        String message = postService.deletePost(postId,userId);
        ApiResponse response = new ApiResponse(message,true);
        return new ResponseEntity<ApiResponse>(response,HttpStatus.OK);
    }

    @GetMapping("/posts/{postId}")
    public ResponseEntity<Post> findPostByIdHandler(@PathVariable("postId") Integer postId) throws Exception {
        Post post = postService.findPostByPostId(postId);
        return new ResponseEntity<>(post,HttpStatus.OK);
    }

    @GetMapping("/posts/userPosts")
    public ResponseEntity<List<Post>> findUsersPost(@RequestHeader("Authorization") String jwtToken) throws Exception {
        Integer userId = userService.getUserByJWT(jwtToken).getiUserId();
        List<Post> posts = postService.findPostByUserId(userId);
        return new ResponseEntity<>(posts,HttpStatus.OK);
    }

    @GetMapping("/posts")
    public ResponseEntity<List<Post>> findAllPost(){
        List<Post> allPosts = postService.findAllPost();
        return new ResponseEntity<>(allPosts,HttpStatus.OK);
    }

    @PutMapping("/posts/savePost/{postId}")
    public ResponseEntity<Post> savePostHandler(@PathVariable("postId") Integer postId,@RequestHeader("Authorization") String jwtToken) throws Exception{
        Integer userId = userService.getUserByJWT(jwtToken).getiUserId();
        Post post = postService.savedPost(postId,userId);
        return new ResponseEntity<>(post,HttpStatus.ACCEPTED);
    }

    @PutMapping("/posts/likePost/{postId}")
    public ResponseEntity<Post> likePostHandler(@PathVariable("postId") Integer postId,@RequestHeader("Authorization") String jwtToken) throws Exception{
        Integer userId = userService.getUserByJWT(jwtToken).getiUserId();
        Post post = postService.likePost(postId,userId);
        return new ResponseEntity<>(post,HttpStatus.ACCEPTED);
    }
}
