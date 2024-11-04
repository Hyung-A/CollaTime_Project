package com.ohgiraffers.collatime.auth.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/auth")
public class AuthController {


    @GetMapping("/login")
    public void login() {
        System.out.println("auth hi");
    }

    @GetMapping("/fail")
    public ModelAndView loginFail(ModelAndView mv, @RequestParam String message) {
        mv.addObject("message", message);
        mv.setViewName("/auth/fail");

        return mv;
    }

    @GetMapping("/loginResult")
    public ModelAndView loginResult(ModelAndView mv) {
        String message = "로그인에 성공했습니다.";
        mv.addObject("message", message);
        mv.setViewName("/auth/loginResult");

        return mv;
    }

}
