package com.acs.demo.controller;

import com.acs.demo.config.provideJWT;
import com.acs.demo.request.LoginRequest;
import com.acs.demo.models.User;
import com.acs.demo.repository.UserRepository;
import com.acs.demo.response.AuthResponse;
import com.acs.demo.service.UserDetailService;
import com.acs.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserDetailService userDetailService;

    @PostMapping("/signUp")
    // Request body sent on post
    public AuthResponse createUser(@RequestBody User user) throws Exception {

        User isExist = userRepository.findByGmail(user.getGmail());

        if(isExist!=null){
            throw new Exception("Email is already registered");
        }

        User newUser = new User();
        newUser.setiUserId(user.getiUserId());
        newUser.setStrFirstName(user.getStrFirstName());
        newUser.setStrLastName(user.getStrLastName());
        newUser.setGmail(user.getGmail());
        newUser.setStrPassword(passwordEncoder.encode(user.getStrPassword()));
        newUser.setGender(user.getGender());

        // save the new created user in sql table
        User savedUser = userRepository.save(newUser);

        Authentication authentication = new UsernamePasswordAuthenticationToken(savedUser.getGmail(),user.getStrPassword());

        String token = provideJWT.generateToken(authentication);

        return new AuthResponse(token,"Register Success");
    }

    @PostMapping("/signIn")
    public AuthResponse signIn(@RequestBody LoginRequest loginRequest){
        Authentication authentication = authenticate(loginRequest.getEmail(),loginRequest.getPassword());

        String token = provideJWT.generateToken(authentication);

        return new AuthResponse(token,"SignIn Successfully");
    }

    private Authentication authenticate(String email, String password) {
        UserDetails userDetails = userDetailService.loadUserByUsername(email);

        if(userDetails == null ){
            throw new BadCredentialsException("Invalid user name");
        }
        if(!passwordEncoder.matches(password,userDetails.getPassword())){
            throw new BadCredentialsException("Incorrect password entered");
        }

        return new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
    }
}
