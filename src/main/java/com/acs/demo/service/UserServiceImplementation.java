package com.acs.demo.service;

import com.acs.demo.config.provideJWT;
import com.acs.demo.models.User;
import com.acs.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class UserServiceImplementation implements UserService{

    @Autowired
    UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User registerUser(User user) {
        User newUser = new User();
        newUser.setiUserId(user.getiUserId());
        newUser.setStrFirstName(user.getStrFirstName());
        newUser.setStrLastName(user.getStrLastName());
        newUser.setGmail(user.getGmail());
        newUser.setStrPassword(user.getStrPassword());
        newUser.setGender(user.getGender());

        // save the new created user in sql table
        return userRepository.save(newUser);
    }

    @Override
    public User findUserById(Integer userId) throws Exception{
        Optional<User> user = userRepository.findById(userId);

        if(user.isPresent()) {
            return user.get();
        }else{
            throw new NoSuchElementException("User with ID: " + userId + " doesn't exist.");
        }
    }

    @Override
    public User findUserByEmail(String gmail) {
        return userRepository.findByGmail(gmail);
    }

    @Override
    public User followUser(Integer userId1, Integer userId2) throws Exception{
        User user1 = findUserById(userId1);
        User user2 = findUserById(userId2);

        if(!user2.getFollowers().contains(userId1)){
            user2.getFollowers().add(user1.getiUserId());
        }
        if(!user1.getFollowing().contains(userId2)){
            user1.getFollowing().add(user2.getiUserId());
        }

        userRepository.save(user1);
        userRepository.save(user2);

        return user1;
    }

    @Override
    public User updateUser(User user , Integer userId) throws Exception{
        Optional<User> userToModify = userRepository.findById(userId);

        if(userToModify.isEmpty()){
            throw new NoSuchElementException("User with ID: " + userId + " doesn't exist.");
        }

        User modifiedUser = userToModify.get();

        if(user.getStrFirstName() != null) modifiedUser.setStrFirstName(user.getStrFirstName());
        if(user.getStrLastName() != null) modifiedUser.setStrLastName(user.getStrLastName());
        if(user.getGmail() != null) modifiedUser.setGmail(user.getGmail());
        if(user.getStrPassword() != null) modifiedUser.setStrPassword(passwordEncoder.encode(user.getStrPassword()));
        if(user.getGender() != null) modifiedUser.setGender(user.getGender());

        userRepository.save(modifiedUser);
        return modifiedUser;
    }

    @Override
    public List<User> searchUser(String query) {
        return userRepository.searchUser(query);
    }

    public String deleteUser(Integer userId){

        Optional<User> userToBeDeleted = userRepository.findById(userId);

        if(userToBeDeleted.isPresent()){
            userRepository.deleteById(userId);
            return "User with Id : " + userId +" deleted successfully";
        }else{
            throw new NoSuchElementException("User with ID: " + userId + " doesn't exist.");
        }
    }

    @Override
    public User getUserByJWT(String jwtToken) {
        String email = provideJWT.getEmailFromJwtToken(jwtToken);

        return userRepository.findByGmail(email);
    }
}
