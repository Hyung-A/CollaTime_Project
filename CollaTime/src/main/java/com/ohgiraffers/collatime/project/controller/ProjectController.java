package com.ohgiraffers.collatime.project.controller;


import com.ohgiraffers.collatime.mail.model.dto.MailDTO;
import com.ohgiraffers.collatime.mail.model.service.MailService;
import com.ohgiraffers.collatime.project.model.dto.InviteMemberDTO;
import com.ohgiraffers.collatime.project.model.dto.ProjectDTO;
import com.ohgiraffers.collatime.project.model.service.ProjectService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;


@Controller
@RequestMapping("/project")
public class ProjectController {

    private final ProjectService projectService;
    private final MailService mailService;

    public ProjectController(ProjectService projectService, MailService mailService){
        this.projectService = projectService;
        this.mailService = mailService;

    }
    @GetMapping(value="/projectmain")
    public ModelAndView projectmain(ModelAndView mv){
        projectService.getList().forEach(System.out::println);
        mv.addObject("projectList", projectService.getList());
        mv.setViewName("/project/projectmain");
        return mv;
    }

    @PostMapping(value = "/projectmain", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public List<ProjectDTO> insertProject(ModelAndView mv, @RequestBody ProjectDTO projectDTO){

        System.out.println("포스트 매핑 연결 완료" + projectDTO);  // 데이터 잘 넘어옴

        // insert project
        projectService.insertProject(projectDTO);

//        System.out.println("projectDTO.getProjectNo() : " + projectDTO.getProjectNo());

        // inviteMemberDTO 신규 프로젝트No를 입력
        // 참가 랜덤 코드 inviteMemberDTO에 참가코드 입력
        // insert inviteMember
        InviteMemberDTO inviteMemberDTO = new InviteMemberDTO();
        List<InviteMemberDTO> inviteMemberList = projectDTO.getInviteMemberList();
        for(int i = 0; i < inviteMemberList.size(); i++){
            InviteMemberDTO inviteList = inviteMemberList.get(i);
            inviteList.setProjectNo(projectDTO.getProjectNo());
            inviteList.setJoinCode(createJoinCode());
            projectService.insertJoinProject(inviteList);
            System.out.println(inviteList);
        }

        return projectService.getList();
    }

    // 프로젝트 참가 코드 발생 함수 -> 팀장님이 작성하신 코드 참조(코드 길이만 변형)
    private String createJoinCode(){
        // 33 ~ 38 : 특수 기호(! " # $ % &)
        // 48 ~ 57 : 숫자(0 1 2 3 4 5 6 7 8 9)
        // 65 ~ 90 : 영문자 대문자
        // 97 ~ 122 : 영문자 소문자
        int min = 33;
        int max = 122;
        int codeLength = 8;
        Random random = new Random();

        String randomJoinCode = random.ints(min, max + 1)
                .filter(i -> (i <= 38 || i >= 48) && (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(codeLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
        System.out.println("코드" + randomJoinCode);

        return randomJoinCode;
    }

    // 메일 발송


    // 프로젝트 내용 조회
    @GetMapping(value="selectproject", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public ProjectDTO selectSpecificProject(ModelAndView mv, @ModelAttribute ProjectDTO projectDTO){
        projectDTO = projectService.getProject(projectDTO);
        mv.addObject("select", projectService.getProject(projectDTO));
        System.out.println("select/update = " + projectDTO);
        mv.setViewName("/project/projectmain");
        return projectDTO;

    }

    // 프로젝트 내용 수정
    @PostMapping("/updateproject")
    public ModelAndView updateProject(ModelAndView mv, @ModelAttribute ProjectDTO projectDTO){
        projectService.updateProject(projectDTO);
        InviteMemberDTO inviteMemberDTO = new InviteMemberDTO();
//        projectService.updateMember(inviteMemberDTO);
        mv.addObject("projectList", projectService.getList());
        System.out.println("update" + projectDTO);
        mv.setViewName("redirect:/project/projectmain");
        return mv;
    }

    // 프로젝트 삭제
    @PostMapping("/deleteproject")
    public ModelAndView deleteProject(ModelAndView mv, @ModelAttribute ProjectDTO projectDTO){
        projectService.deleteProject(projectDTO);
        InviteMemberDTO inviteMemberDTO = new InviteMemberDTO();
//        projectService.deleteMember(inviteMemberDTO);
        mv.addObject("projectList", projectService.getList());
        System.out.println("delete" + projectDTO);
        mv.setViewName("redirect:/project/projectmain");
        return mv;
    }


}
