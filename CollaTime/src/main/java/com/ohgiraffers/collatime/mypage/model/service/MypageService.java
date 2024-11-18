package com.ohgiraffers.collatime.mypage.model.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class MypageService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    public boolean pwdCheck(String password, String checkPwd) {

        String comparePwd = checkPwd.replace("\"", "");


        return passwordEncoder.matches(comparePwd, password);
    }
}
