package com.acs.demo.service;

import com.acs.demo.models.Comment;
import com.acs.demo.models.Post;
import com.acs.demo.models.User;
import com.acs.demo.repository.CommentRepository;
import com.acs.demo.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class CommentServiceImplementation implements CommentService{

    @Autowired
    private UserService userService;

    @Autowired
    private PostService postService;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PostRepository postRepository;

    @Override
    public Comment createComment(Comment comment, Integer postId, Integer userId) throws Exception {
        User user = userService.findUserById(userId);
        Post post = postService.findPostByPostId(postId);

        comment.setUser(user);
        comment.setCreatedAt(LocalDateTime.now());
        comment.setContent(comment.getContent());

        Comment newComment = commentRepository.save(comment);

        post.getComments().add(newComment);

        postRepository.save(post);

        return newComment;
    }

    @Override
    public Comment likeComment(Integer commentId, Integer userId) throws Exception {
        Comment  comment = findCommentById(commentId);
        User user = userService.findUserById(userId);

        if(!comment.getLiked().contains(user)){
            comment.getLiked().add(user);
        }else{
            comment.getLiked().remove(user);
        }
        return commentRepository.save(comment);
    }

    @Override
    public Comment findCommentById(Integer commentId) throws Exception {
        Optional<Comment> opt = commentRepository.findById(commentId);

        if(opt.isEmpty()){
            throw new Exception("Comment Not Found");
        }

        return opt.get();
    }
}
