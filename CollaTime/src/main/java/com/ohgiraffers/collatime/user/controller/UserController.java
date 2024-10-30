package com.ohgiraffers.collatime.user.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {
    @GetMapping("/auth/login")
    public String login(){
        return "/auth/login";
    }

    @GetMapping("/user/signup")
    public String signup(){
        return "/user/signup";
    }
}
