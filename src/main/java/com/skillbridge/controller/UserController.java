package com.skillbridge.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skillbridge.entity.User;
import com.skillbridge.service.UserService;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "*")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {

        this.userService = userService;

    }


    @PostMapping("/register")
    public User registerUser(@RequestBody User user) {

        return userService.saveUser(user);

    }


    
    @PostMapping("/login")
public User login(@RequestBody User user) {

    return userService.loginUser(
            user.getEmail(),
            user.getPassword()
    );
}


    @GetMapping("/all")
    public List<User> getAllUsers() {

        return userService.getAllUsers();

    }


    @GetMapping("/count")
    public long getUserCount() {

        return userService.getUserCount();

    }

}