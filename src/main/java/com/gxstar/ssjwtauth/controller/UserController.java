package com.gxstar.ssjwtauth.controller;

import com.gxstar.ssjwtauth.model.User;
import com.gxstar.ssjwtauth.service.api.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    private final UserService userService;

    @PostMapping("/sign-up")
    public User signUp(@RequestBody final User user) {
        return userService.save(user);
    }

    @GetMapping
    public List<User> getUsers() {
        return userService.getUsers();
    }
}
