package com.ohgiraffers.collatime.mail.controller;

import com.ohgiraffers.collatime.mail.MailDTO;
import com.ohgiraffers.collatime.mail.MailService;
import com.ohgiraffers.collatime.user.model.dto.UserDTO;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/mail")
public class MailPageController {

    @Autowired
    private final MailService mailService;

    public MailPageController(MailService mailService){
        this.mailService = mailService;
    }

    @PostMapping(value = "/sendMail", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public MailDTO sendMail(@RequestBody String data) throws MessagingException {
        System.out.println("메일값 넘어옴");
        System.out.println(data);
        return mailService.sendMail(data);
    }
}
