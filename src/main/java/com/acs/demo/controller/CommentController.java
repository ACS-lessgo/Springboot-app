package com.acs.demo.controller;

import com.acs.demo.models.Comment;
import com.acs.demo.models.User;
import com.acs.demo.service.CommentService;
import com.acs.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private UserService userService;

    @PostMapping("/createComment/post/{postId}")
    public Comment createComment(@RequestBody Comment comment,@RequestHeader("Authorization") String jwt,@PathVariable("postId") Integer postId) throws Exception {
        User user = userService.getUserByJWT(jwt);

        return commentService.createComment(comment,postId, user.getiUserId());
    }

    @PutMapping("/api/comments/like/{commentId}")
    public Comment likedComment(@RequestHeader("Authorization") String jwt,@PathVariable("commentId") Integer commentId) throws Exception {
        User user = userService.getUserByJWT(jwt);

        return commentService.likeComment(commentId, user.getiUserId());
    }
}
