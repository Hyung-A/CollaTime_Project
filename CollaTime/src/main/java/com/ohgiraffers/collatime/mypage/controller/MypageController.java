package com.ohgiraffers.collatime.mypage.controller;

import com.ohgiraffers.collatime.auth.model.AuthDetails;
import com.ohgiraffers.collatime.mypage.model.service.MypageService;
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


    @GetMapping
    public String mypagemain() {
        return "/mypage/mypagemain";
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
    public ModelAndView modifyuser(ModelAndView mv, @ModelAttribute UserDTO userDTO, @AuthenticationPrincipal AuthDetails authDetails) {
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
            message = "수정에 실패했습니다.";
            System.out.println("no");
            isPass = false;
        }

        System.out.println(isPass);
        mv.addObject("message", message);
        mv.addObject("isPass", isPass);

        mv.setViewName("/mypage/resultmypagemodal");

        return mv;
    }

    @GetMapping("/deleteuser")
    public ModelAndView signup(ModelAndView mv, @AuthenticationPrincipal AuthDetails authDetails) {
        int userNo = authDetails.getUserNo();

        int result = userService.deleteUser(userNo);

        boolean isPass = true;
        String message = "";

        if (result > 0) {
            message = "그동안 CollaTime을 사용해주셔서 감사합니다.";
            System.out.println("yes");
        } else {
            message = "삭제에 실패했습니다.";
            System.out.println("no");
            isPass = false;
        }

        mv.addObject("message", message);
        mv.addObject("isPass", isPass);

        mv.setViewName("resultmypagemodal");

        return mv;
    }

    @GetMapping(value = "/modifyprofile", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public int modifyprofile(@AuthenticationPrincipal AuthDetails authDetails){
        int userNo = authDetails.getUserNo();
        int randomPicture = (int) (Math.random() * 5) + 1;
        int randomColor = (int) (Math.random() * 5) + 1;
        String userPicture = "";
        String userColor = "";
        switch (randomPicture){
            case 1 : userPicture = "profile_rat"; break;
            case 2 : userPicture = "profile_cat"; break;
            case 3 : userPicture = "profile_bear"; break;
            case 4 : userPicture = "profile_wolf"; break;
            case 5 : userPicture = "profile_chick"; break;
        }
        switch (randomColor){
            case 1 : userColor = "red"; break;
            case 2 : userColor = "brown"; break;
            case 3 : userColor = "blue"; break;
            case 4 : userColor = "green"; break;
            case 5 : userColor = "yellow"; break;
        }

        System.out.println("userNo = " + userNo);
        System.out.println("userPicture = " + userPicture);
        System.out.println("userColor = " + userColor);

        userService.modifyProfile(userNo, userPicture, userColor);
//        mv.addObject("userPicture", userPicture);
//        mv.addObject("userColor", userColor);
//        mv.setViewName("mypage/mypagemain");
//
//        return mv;
        return 1;
    }

}
