package com.ohgiraffers.collatime.mail.controller;


import com.ohgiraffers.collatime.mail.model.dto.MailDTO;
import com.ohgiraffers.collatime.mail.model.service.MailService;
import com.ohgiraffers.collatime.project.model.dto.ProjectDTO;
import com.ohgiraffers.collatime.project.model.service.ProjectService;
import com.ohgiraffers.collatime.user.model.dto.UserDTO;
import com.ohgiraffers.collatime.user.model.service.UserService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/mail")
public class MailPageController {

    @Autowired
    private final UserService userService;

    @Autowired
    private final MailService mailService;

    public MailPageController(MailService mailService, UserService userService){
        this.mailService = mailService;
        this.userService = userService;
    }

    @PostMapping(value = "/sendnewmail", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public MailDTO sendSignupMail(@RequestBody String data) throws MessagingException {
        List<UserDTO> user = userService.selectAllUser();
        List<String> checkMail = new ArrayList<>();

        for (int i = 0; i < user.size(); i++) {
            checkMail.add(user.get(i).getUserEmail());
        }

        MailDTO mail = new MailDTO();

        String email = data.replaceAll("\"", "");
        if(!checkMail.contains(email)) {
            mail = mailService.sendSignUp(email, "회원가입");
        }

        return mail;
    }

    @PostMapping(value = "/sendexistingmail", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public MailDTO sendMail(@RequestBody String mail) throws MessagingException {
        List<UserDTO> user = userService.selectAllUser();
        List<String> checkMail = new ArrayList<>();

        for (int i = 0; i < user.size(); i++) {
            checkMail.add(user.get(i).getUserEmail());
        }

        MailDTO Email = new MailDTO();

        String email = mail.replaceAll("\"", "");

        if(checkMail.contains(email)) {
            Email = mailService.sendSignUp(email, "아이디/비밀번호 찾기");
        }

        return Email;
    }

    @PostMapping(value = "/sendnewpwdmail", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public MailDTO sendNewPwdMail(@RequestBody String mail) throws MessagingException {

        MailDTO Email = new MailDTO();

        String email = mail.replaceAll("\"", "");
        Email = mailService.sendPwd(email);

        return Email;
    }

}
