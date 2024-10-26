package com.acs.demo.controller;

import com.acs.demo.models.User;
import com.acs.demo.repository.UserRepository;
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

    @PostMapping("users/createUser")
    // Request body sent on post
    public User createUser(@RequestBody User user){
        User newUser = new User();
        newUser.setiUserId(user.getiUserId());
        newUser.setStrFirstName(user.getStrFirstName());
        newUser.setStrLastName(user.getStrLastName());
        newUser.setStrEmailId(user.getStrEmailId());
        newUser.setStrPassword(user.getStrPassword());

        // save the new created user in sql table
        User saveUser = userRepository.save(newUser);
        return newUser;
    }

    @GetMapping("/users")
    public List<User> getUsers(){
        return userRepository.findAll();
    }

    @GetMapping("/users/{userId}")
    // PathVariable to access userId
    // defining a new variable id so getting the userId
    public User getUsersById(@PathVariable("userId")Integer userId) throws Exception {
        Optional<User> user = userRepository.findById(userId);

        if(user.isPresent()) {
            return user.get();
        }else{
            throw new NoSuchElementException("User with ID: " + userId + " doesn't exist.");
        }
    }

    @PutMapping("users/modifyUser/{userId}")
    public User updateUserDetails(@RequestBody User user,@PathVariable("userId") Integer userId ){
        Optional<User> userToModify = userRepository.findById(userId);

        if(userToModify.isEmpty()){
            throw new NoSuchElementException("User with ID: " + userId + " doesn't exist.");
        }

        User modifiedUser = userToModify.get();

        if(user.getStrFirstName() != null) modifiedUser.setStrFirstName(user.getStrFirstName());
        if(user.getStrLastName() != null) modifiedUser.setStrLastName(user.getStrLastName());
        if(user.getStrEmailId() != null) modifiedUser.setStrEmailId(user.getStrEmailId());
        if(user.getStrPassword() != null) modifiedUser.setStrPassword(user.getStrPassword());

        userRepository.save(modifiedUser);
        return modifiedUser;
    }

    @DeleteMapping("users/deleteUser/{userId}")
    public String deleteUser(@PathVariable("userId") Integer userId){

        Optional<User> userToBeDeleted = userRepository.findById(userId);

        if(userToBeDeleted.isPresent()){
            userRepository.deleteById(userId);
            return "User with Id : " + userId +" deleted successfully";
        }else{
            throw new NoSuchElementException("User with ID: " + userId + " doesn't exist.");
        }
    }
}
