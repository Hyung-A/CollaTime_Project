package com.ohgiraffers.collatime.common;

import com.ohgiraffers.collatime.auth.model.AuthDetails;
import com.ohgiraffers.collatime.user.model.dto.UserDTO;
import com.ohgiraffers.collatime.user.model.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value= {"/", "/main"})
public class MainController {

    @Autowired
    private UserService userService;

    @GetMapping
    public String main(){
        return "/common/main";
    }

    @GetMapping(value = "/common/profile", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public List<String> profile(@AuthenticationPrincipal AuthDetails authDetails){
        String username = authDetails.getUsername();
        UserDTO userDTO = userService.findByUsername(username);
        String userPicture = userDTO.getUserPicture();
        String userColor = userDTO.getUserColor();

        System.out.println("userPicture = " + userPicture);
        System.out.println("userColor = " + userColor);

        List<String> profileData = new ArrayList<>();

        profileData.add(userPicture);
        profileData.add(userColor);
        System.out.println("profileData = " + profileData);

        return profileData;
    }

}
