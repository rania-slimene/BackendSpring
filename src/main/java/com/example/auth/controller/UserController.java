package com.example.auth.controller;

import com.example.auth.entities.User;
import com.example.auth.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/")
@CrossOrigin("*")
public class UserController {
    @Autowired
    UserService userService;
    @PostMapping("Create")
    public User SignUp (@RequestBody User user){
        return userService.SignUp(user);

    }

    @PostMapping("login")
    public User SignIn (@RequestBody User user){
        return  userService.SignIn(user);
    }
}
