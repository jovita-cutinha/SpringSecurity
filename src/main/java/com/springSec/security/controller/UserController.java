package com.springSec.security.controller;

import com.springSec.security.model.Users;
import com.springSec.security.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public List<Users> getUsers() {
        return userService.getUsers();
    }

    @PostMapping("/addUser")
    public Users addUser(@RequestBody Users user){
        return userService.addUser(user);
    }

    @PostMapping("/login")
    public String login(@RequestBody Users user){
        return userService.verify(user);
    }

}
