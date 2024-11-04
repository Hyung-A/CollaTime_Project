package com.ohgiraffers.collatime.auth.controller;

import com.ohgiraffers.collatime.auth.model.AuthDetails;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
    public ModelAndView loginResult(ModelAndView mv, @AuthenticationPrincipal AuthDetails authDetails) {
//        session에 담겨있는 회원의 값을 불러오려면 @@AuthenticationPrincipal AuthDetails authDetails 을 매개변수로 지정하고 get메서드로 값 당겨오기
        System.out.println(authDetails.getUserNo());
        String message = "로그인에 성공했습니다.";
        mv.addObject("message", message);
        mv.setViewName("/auth/loginResult");

        return mv;
    }

}
