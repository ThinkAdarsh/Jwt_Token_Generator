package com.jwt.token.controller;

import com.jwt.token.Model.User;
import com.jwt.token.Model.UserAuthDTO;
import com.jwt.token.Model.UserAuthResponse;
import com.jwt.token.Service.UserService;
import com.jwt.token.ServiceIMPL.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtService jwtService;


    @PostMapping("/signup")
    public ResponseEntity createUser(@RequestBody User userInfo) {
        return ResponseEntity.ok().body(userService.createUser(userInfo));
    }

    @PostMapping("/login")
    public UserAuthResponse authenticateAndGetToken(@RequestBody UserAuthDTO authRequest) {
        System.out.println("authRequest = " + authRequest);
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        if (authentication.isAuthenticated()) {
            UserAuthResponse userAuthResponse = new UserAuthResponse();
            userAuthResponse.setToken(jwtService.generateToken(authRequest.getUsername()));
            userAuthResponse.setMessage("Valid Credentials");
            return userAuthResponse;
        } else {
            throw new UsernameNotFoundException("Invalid Credentials");
        }
    }

    @GetMapping("/")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }


}
