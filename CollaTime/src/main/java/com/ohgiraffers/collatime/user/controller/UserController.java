package com.ohgiraffers.collatime.user.controller;

import com.ohgiraffers.collatime.user.model.dto.SignupDTO;
import com.ohgiraffers.collatime.user.model.dto.UserDTO;
import com.ohgiraffers.collatime.user.model.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

//    회원가입 흐름
//    회원가입 시에는 아이디, 비밀번호, 이메일, 닉네임 유효성을 판단해야한다.
//    아이디, 이메일, 닉네임은 DB와 비교해야하므로 post로 받아서 db랑 비교해 model로 반환하는 걸로 해보자
//    비밀번호는 view에서 직접 비교가 가능하면 바로 하자
//    이용약관에 대한 박스체크는 js로 구현하자
//    이메일 코드는 과연 어떻게 비교해야할까? 이메일 유효성을 판단한 뒤 세션으로 담아놨다가 비교하는거? 근데 이거는 F12로 볼 수 있지 않을까?
//    모든 유효성이 통과했는지에 대한 true 와 false를 판단할 수 있는 무언가를 만들자
//    ㄴ ex) model.addObject("idPass", true)
//          그 다음 true인지 아닌지 판단하기
//          만약 Str로 넘겨야한다면 Boolean.parseBoolean()로 idPass값 뽑아내기
    

    @GetMapping("/signup")
    public void signup(){
        System.out.println("hi");
    }

    @PostMapping("/signup")
    public ModelAndView signup(ModelAndView mv, @ModelAttribute SignupDTO signupDTO){
        System.out.println("hi post");
        System.out.println(signupDTO);
        int result = userService.registUser(signupDTO);



        System.out.println(result);
        String message = "";
        boolean isSuccess = true;

        if(result>0){
            message = "회원가입이 완료되었습니다.";
            isSuccess = true;
            mv.setViewName("auth/login");
            System.out.println("yes");
        }else{
            message = "내용을 다시 확인해주세요.";
            isSuccess = false;
            System.out.println("no");
            mv.setViewName("user/signup");

        }

        mv.addObject("message", message);

        return mv;
    }

    @GetMapping(value = "check", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public List<UserDTO> Check(){
        userService.selectAllUser().forEach(System.out::println);

        return userService.selectAllUser();
    }
}
