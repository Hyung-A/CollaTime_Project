package com.ohgiraffers.collatime.project.controller;


import com.ohgiraffers.collatime.auth.model.AuthDetails;
import com.ohgiraffers.collatime.mail.model.service.MailService;
import com.ohgiraffers.collatime.project.model.dto.InviteMemberDTO;
import com.ohgiraffers.collatime.project.model.dto.MemberListDTO;
import com.ohgiraffers.collatime.project.model.dto.ProjectDTO;
import com.ohgiraffers.collatime.project.model.service.ProjectService;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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

    // 메인 화면에서 내가 참여하는 프로젝트 전체 조회
    @GetMapping(value="/projectmain")
    public ModelAndView projectmain(HttpServletRequest req, ModelAndView mv, @AuthenticationPrincipal AuthDetails authDetails){
        HttpSession session = req.getSession();
        Enumeration<String> attrNames = session.getAttributeNames();
        while (attrNames.hasMoreElements()){
            String attr = attrNames.nextElement();
            System.out.println(attr + " : " + session.getAttribute(attr));

        }

        int userNo = 0;
        if(authDetails != null){
            userNo = authDetails.getUserNo();
        }
        System.out.println("userNo : " + userNo);
        projectService.getList(userNo).forEach(System.out::println);
        mv.addObject("projectList", projectService.getList(userNo));
        mv.setViewName("/project/projectmain");
        return mv;
    }

    // 프로젝트 생성
    @PostMapping(value = "/projectmain", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public List<ProjectDTO> insertProject(ModelAndView mv, @RequestBody ProjectDTO projectDTO, @AuthenticationPrincipal AuthDetails authDetails) throws MessagingException {

        System.out.println("포스트 매핑 연결 완료" + projectDTO);  // 데이터 잘 넘어옴

        // insert project
        projectService.insertProject(projectDTO);

//        System.out.println("projectDTO.getProjectNo() : " + projectDTO.getProjectNo());

        int userNo = 0;
        if(authDetails != null){
            userNo = authDetails.getUserNo();
        }

        // inviteMemberDTO 신규 프로젝트No를 입력
        // 참가 랜덤 코드 inviteMemberDTO에 참가코드 입력
        // insert inviteMember
        List<InviteMemberDTO> inviteMemberList = projectDTO.getInviteMemberList();
        for(int i = 0; i < inviteMemberList.size(); i++){
            // DB 저장
            String createJoinCode = createJoinCode();
            InviteMemberDTO inviteList = inviteMemberList.get(i);
            inviteList.setProjectNo(projectDTO.getProjectNo());
            inviteList.setJoinCode(createJoinCode);
            projectService.insertJoinProject(inviteList);
            System.out.println(inviteList);


            // 메일 보내기
            String email = inviteList.getEmail();

//            MailDTO mailDTO = new MailDTO();
            mailService.sendJoinCodeMail(email, createJoinCode);

        }
        mv.setViewName("redirect:/project/projectmain");

        return projectService.getList(userNo);
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

    // 프로젝트 내용 조회
    @GetMapping(value="selectproject", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public ProjectDTO selectSpecificProject(ModelAndView mv, @ModelAttribute ProjectDTO projectDTO){
        projectDTO = projectService.getProject(projectDTO);
        List<InviteMemberDTO> inviteMemberList = projectService.getInviteMemberList(projectDTO);
        projectDTO.setInviteMemberList(inviteMemberList);
        mv.addObject("select", projectService.getProject(projectDTO));
        System.out.println("select/update = " + projectDTO);
        mv.setViewName("/project/projectmain");
        return projectDTO;

    }

    // 프로젝트 내용 수정
    @PostMapping("/updateproject")
    public ModelAndView updateProject(ModelAndView mv, @ModelAttribute ProjectDTO projectDTO, @AuthenticationPrincipal AuthDetails authDetails){
        projectService.updateProject(projectDTO);
        InviteMemberDTO inviteMemberDTO = new InviteMemberDTO();
        inviteMemberDTO.setProjectNo(projectDTO.getProjectNo());
        System.out.println("수정할 프로젝트 번호다" + inviteMemberDTO.getProjectNo());
//        projectService.updateMember(inviteMemberDTO);

        int userNo = 0;
        if(authDetails != null){
            userNo = authDetails.getUserNo();
        }

        mv.addObject("projectList", projectService.getList(userNo));
        System.out.println("update" + projectDTO);
        mv.setViewName("redirect:/project/projectmain");
        return mv;
    }

    // 프로젝트 삭제
    @PostMapping("/deleteproject")
    public ModelAndView deleteProject(ModelAndView mv, @ModelAttribute ProjectDTO projectDTO, @AuthenticationPrincipal AuthDetails authDetails){
        projectService.deleteProject(projectDTO);
        InviteMemberDTO inviteMemberDTO = new InviteMemberDTO();
        inviteMemberDTO.setProjectNo(projectDTO.getProjectNo());
        System.out.println("삭제할 프로젝트 번호다" + inviteMemberDTO.getProjectNo());
        projectService.deleteMember(inviteMemberDTO);

        int userNo = 0;
        if(authDetails != null){
            userNo = authDetails.getUserNo();
        }

        mv.addObject("projectList", projectService.getList(userNo));
        System.out.println("delete" + projectDTO);
        mv.setViewName("redirect:/project/projectmain");
        return mv;
    }

    // joinCode 전체 조회하기
    @GetMapping(value = "selectJoinCode", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public List<InviteMemberDTO> selectJoinCode(ModelAndView mv, @ModelAttribute InviteMemberDTO inviteMemberDTO){
        System.out.println("컨트롤러다아아아");

        List<InviteMemberDTO> inviteMemberList = projectService.getJoinCodeList();

        System.out.println("조회 후 inviteMemberList" + inviteMemberList);

        return inviteMemberList;
    }

    @PostMapping(value="updateUserNo")
    public ModelAndView updateUserNo (ModelAndView mv, @RequestBody InviteMemberDTO inviteMemberDTO){
        System.out.println("updateUserNo 포스트 매핑 성공" + inviteMemberDTO);

        projectService.updateUserNo(inviteMemberDTO);

        mv.setViewName("redirect:/project/projectmain");

        return mv;
    }

    // 팀원 관리 모달  - 팀원 정보 조회
    @GetMapping(value="selectMember", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public List<MemberListDTO> getManageMember(ModelAndView mv, @ModelAttribute MemberListDTO memberListDTO, @ModelAttribute ProjectDTO projectDTO){

        List<MemberListDTO> memberList = projectService.getMemberManager(projectDTO);
        System.out.println(memberList);
        mv.addObject("memberManagement", memberList);
        mv.setViewName("redierect:/project/projectmain");

        return memberList;

    }

    // 팀원 관리 모달에서 멤버 추가 했을 때 이메일 전송 및 DB에 넣기
    @PostMapping(value = "/sendUpdatedEmail", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public void sendUpdatedEmail(@RequestBody ProjectDTO projectDTO) throws MessagingException{

        System.out.println("야야야 프로젝트 디티오다" + projectDTO);
        System.out.println("sendUpdateEmail 포스트 매핑 성공");
        List<InviteMemberDTO> moreInviteMemberList = projectDTO.getInviteMemberList();
        for(int i = 0; i < moreInviteMemberList.size(); i++){
            String createJoinCode = createJoinCode();
            InviteMemberDTO moreInviteList = moreInviteMemberList.get(i);
            moreInviteList.setProjectNo(projectDTO.getProjectNo());
            moreInviteList.setProjectNo(projectDTO.getProjectNo());
            moreInviteList.setJoinCode(createJoinCode);
            projectService.insertJoinProject(moreInviteList);
            System.out.println(moreInviteList);

            String email = moreInviteList.getEmail();

//            MailDTO mailDTO = new MailDTO();
            mailService.sendJoinCodeMail(email, createJoinCode);
        }
    }


}
