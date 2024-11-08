package com.ohgiraffers.collatime.mypage.controller;

import com.ohgiraffers.collatime.auth.model.AuthDetails;
import com.ohgiraffers.collatime.common.UserRole;
import com.ohgiraffers.collatime.mypage.model.service.MypageService;
import com.ohgiraffers.collatime.user.model.dto.SignupDTO;
import com.ohgiraffers.collatime.user.model.dto.UserDTO;
import com.ohgiraffers.collatime.user.model.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/mypage")
public class MypageController {

    @Autowired
    private MypageService mypageService;

    @Autowired
    private UserService userService;


    @GetMapping("/mypagemain")
    public void mypagemain(@AuthenticationPrincipal AuthDetails authDetails) {
        System.out.println("hi");
    }

    @PostMapping("/pwdcheck")
    @ResponseBody
    public boolean pwdcheck(@AuthenticationPrincipal AuthDetails authDetails, @RequestBody String checkPwd){

        System.out.println("들어왔냐"+authDetails.getPassword());
        System.out.println(checkPwd);

        boolean pwdCheck = mypageService.pwdCheck(authDetails.getPassword(), checkPwd);

        return pwdCheck;
    }

    @PostMapping("/modifyuser")
    public ModelAndView signup(ModelAndView mv, @ModelAttribute UserDTO userDTO, @AuthenticationPrincipal AuthDetails authDetails) {
        userDTO.setUserNo(authDetails.getUserNo());

        boolean isModifyPwd = true;

        if(userDTO.getUserName().isEmpty()){
            userDTO.setUserName(authDetails.getUserName());
        }
        if(userDTO.getUserNickname().isEmpty()){
            userDTO.setUserNickname(authDetails.getUserNickname());
        }
        if(userDTO.getUserEmail().isEmpty()){
            userDTO.setUserEmail(authDetails.getUserEmail());
        }
        if(userDTO.getUserPwd().isEmpty()){
            userDTO.setUserPwd(authDetails.getPassword());
            isModifyPwd = false;
        }

        System.out.println(isModifyPwd);
        System.out.println("hi"+userDTO);
        int result = userService.modifyUser(userDTO, isModifyPwd);


        System.out.println(result);
        String message = "";
        boolean isPass = true;

        if (result > 0) {
            message = "수정이 완료되었습니다.";
            System.out.println("yes");

        } else {
            message = "내용을 다시 확인해주세요.";
            System.out.println("no");
            isPass = false;
        }

        System.out.println(isPass);
        mv.addObject("message", message);
        mv.addObject("isPass", isPass);

        mv.setViewName("/mypage/resultsmallmodal");

        return mv;
    }
}