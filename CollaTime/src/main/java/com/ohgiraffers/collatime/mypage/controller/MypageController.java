package com.ohgiraffers.collatime.mypage.controller;

import com.ohgiraffers.collatime.auth.model.AuthDetails;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/mypage")
public class MypageController {

    @GetMapping("/mypagemain")
    public void mypagemain(@AuthenticationPrincipal AuthDetails authDetails) {
        System.out.println("hi");
    }

}
