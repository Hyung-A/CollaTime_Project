package com.ohgiraffers.collatime.domain.user.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {
    @GetMapping("/domain/auth/login")
    public String login(){
        return "/domain/auth/login";
    }

}
