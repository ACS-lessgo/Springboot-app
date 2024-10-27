package com.acs.demo.controller;

import com.acs.demo.models.User;
import com.acs.demo.repository.UserRepository;
import com.acs.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
public class UserController {

    // spring will initialize an instance of userRepository automatically
    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;

    @PostMapping("users/createUser")
    // Request body sent on post
    public User createUser(@RequestBody User user) {
        return userService.registerUser(user);
    }

    @GetMapping("/api/users")
    public List<User> getUsers(){
        return userRepository.findAll();
    }

    @GetMapping("/users/{userId}")
    // PathVariable to access userId
    // defining a new variable id so getting the userId
    public User getUsersById(@PathVariable("userId")Integer userId) throws Exception {
            return userService.findUserById(userId);
    }

    @PutMapping("users/modifyUser/{userId}")
    public User updateUserDetails(@RequestBody User user,@PathVariable("userId") Integer userId ) throws Exception {
        return userService.updateUser(user,userId);
    }

    @DeleteMapping("users/deleteUser/{userId}")
    public String deleteUser(@PathVariable("userId") Integer userId){
        return userService.deleteUser(userId);
    }

    @GetMapping("users/search")
    public List<User> searchUser(@RequestParam("query") String query){
        return userService.searchUser(query);
    }

    @PutMapping("users/follow/{userId1}/{userId2}")
    public User followUserHandler(@PathVariable("userId1") Integer userId1 , @PathVariable("userId2") Integer userId2) throws Exception {
        return userService.followUser(userId1,userId2);
    }
}
