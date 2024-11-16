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

        System.out.println(checkMail);

        MailDTO mail = new MailDTO();

        System.out.println(data);

        String email = data.replaceAll("\"", "");
        if(!checkMail.contains(email)) {
            mail = mailService.sendMail(email);
        }

        return mail;
    }

    @PostMapping(value = "/sendexistingmail", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public MailDTO sendMail(@RequestBody String mail) throws MessagingException {
        List<UserDTO> user = userService.selectAllUser();
        List<String> checkMail = new ArrayList<>();
        System.out.println(mail);

        for (int i = 0; i < user.size(); i++) {
            checkMail.add(user.get(i).getUserEmail());
        }

        MailDTO Email = new MailDTO();

        String email = mail.replaceAll("\"", "");

        if(checkMail.contains(email)) {
            Email = mailService.sendMail(email);
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
