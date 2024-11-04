package com.ohgiraffers.collatime.mail.controller;

import com.ohgiraffers.collatime.mail.MailService;
import com.ohgiraffers.collatime.user.model.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public Map<String, Objects> sendMail(@RequestBody Map<String, Objects> paramMap) {
//        리턴값을 JSON이 사용할 수 있게 해야한다. fetch에서는 JSON으로 반환을 안하면 실행이 안된다.
        System.out.println("여기까지 온건가?");
        System.out.println(paramMap.get("mail"));

        return paramMap;
    }
}
