package com.ohgiraffers.collatime.mail.controller;

import com.ohgiraffers.collatime.mail.MailDTO;
import com.ohgiraffers.collatime.mail.MailService;
import com.ohgiraffers.collatime.project.model.dto.ProjectDTO;
import com.ohgiraffers.collatime.project.model.service.ProjectService;
import com.ohgiraffers.collatime.user.model.dto.UserDTO;
import com.ohgiraffers.collatime.user.model.service.UserService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/mail")
public class MailPageController {

    @Autowired
    private final UserService userService;
    private final ProjectService projectService;

    @Autowired
    private final MailService mailService;

    public MailPageController(MailService mailService, UserService userService, ProjectService projectService){
        this.mailService = mailService;
        this.userService = userService;
        this.projectService = projectService;
    }

    @PostMapping(value = "/sendNewMail", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public MailDTO sendSignupMail(@RequestBody String data) throws MessagingException {
        System.out.println("회원가입 메일값 넘어옴");
        List<UserDTO> user = userService.selectAllUser();
        List<String> checkMail = new ArrayList<>();

        for (int i = 0; i < user.size(); i++) {
            checkMail.add(user.get(i).getUserEmail());
        }

        System.out.println(checkMail);

        MailDTO mail = new MailDTO();

        System.out.println(data);

        boolean isContain = checkMail.contains(data);
        System.out.println(isContain);

        String email = data.replaceAll("\"", "");
        if(!checkMail.contains(email)) {
            System.out.println("보냄");
            mail = mailService.sendMail(email);
        }else {
            System.out.println("안보냄");
        }

        System.out.println(mail);
        return mail;
    }

    @PostMapping(value = "/sendExistingMail", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public MailDTO sendMail(@RequestBody String mail) throws MessagingException {
        System.out.println("회원확인 메일값 넘어옴");
        List<UserDTO> user = userService.selectAllUser();
        List<String> checkMail = new ArrayList<>();
        System.out.println(mail);

        for (int i = 0; i < user.size(); i++) {
            checkMail.add(user.get(i).getUserEmail());
        }

        MailDTO Email = new MailDTO();

        System.out.println(checkMail.contains(mail));

        String email = mail.replaceAll("\"", "");

        if(checkMail.contains(email)) {
            System.out.println("보냄");
            Email = mailService.sendMail(email);
        }else {
            System.out.println("안보냄");
        }

        System.out.println(Email);
        return Email;
    }

    @PostMapping(value = "/sendNewPwdMail", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public MailDTO sendNewPwdMail(@RequestBody String mail) throws MessagingException {
        System.out.println("새비밀번호 메일값 넘어옴");

        MailDTO Email = new MailDTO();

        String email = mail.replaceAll("\"", "");
        Email = mailService.sendPwd(email);

        return Email;
    }

    // 참가 코드 메일 전송
//    @PostMapping("/deleteproject")
//    public ModelAndView deleteProject(ModelAndView mv, @ModelAttribute ProjectDTO projectDTO){
//        projectService.deleteProject(projectDTO);
//        mv.addObject("projectList", projectService.getList());
//        System.out.println("delete" + projectDTO);
//        mv.setViewName("redirect:/project/projectmain");
//        return mv;
//    }

}
