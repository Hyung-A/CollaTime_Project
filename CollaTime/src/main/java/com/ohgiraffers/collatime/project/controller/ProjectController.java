package com.ohgiraffers.collatime.project.controller;

import com.ohgiraffers.collatime.mail.model.dto.MailDTO;
import com.ohgiraffers.collatime.mail.model.service.MailService;
import com.ohgiraffers.collatime.project.model.dto.InviteMemberDTO;
import com.ohgiraffers.collatime.project.model.dto.ProjectDTO;
import com.ohgiraffers.collatime.project.model.service.ProjectService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;


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

//    @PostMapping(value = "/projectmain", produces = "application/json; charset=UTF-8")
//    @ResponseBody
//    public ModelAndView insertProject(ModelAndView mv, @RequestBody ProjectDTO projectDTO, @RequestBody InviteMemberDTO inviteMemberDTO){
//        System.out.println("포스트 매핑 왔다.");
//        System.out.println(projectDTO);
//
//        projectService.insertProject(projectDTO);
//        mv.addObject("projectList", projectService.getList());
//        mv.setViewName("redirect:/project/projectmain");
//        return mv;
//    }

    @PostMapping(value = "/projectmain", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public ModelAndView insertProject(ModelAndView mv, @RequestBody HashMap<String, Object> data){
        /* body에서 프로젝트 데이터를 뽑아서 프로젝트 DTO에 넣은 후 프로젝트 테이블에 DB 저장 */
        // 각각의 데이터들을 object에서 string으로 형변환
        System.out.println("포스트 매핑 연결 완료" + data);  // 데이터 잘 넘어옴
//        String projectName = (String) data.get("projectName");
//        String projectSummary = (String) data.get("projectSummary");
//        String projectPurpose = (String) data.get("projectPurpose");
//        String startDate = (String) data.get("startDate");
//        String endDate = (String) data.get("endDate");
//        String projectCategoryCode = (String) data.get("projectCategoryCode");
//        // 프로젝트 DTO에 데이터 값 초기화
//        ProjectDTO projectDTO = new ProjectDTO(projectName, projectSummary, projectPurpose, startDate, endDate, projectCategoryCode);
//
//        projectService.insertProject(projectDTO);
//        mv.addObject("projectList", projectService.getList());
//        mv.setViewName("redirect:/project/projectmain");

        /* -------------------------------------------------------------------------------------------------------- */
        /* body에서 메일 데이터를 받아와서 메일 DTO에 넣고 메일 DB에 저장 */
        // object로 받아온 값 String/int로 형변환

        // 난수 발생 함수 호출
        // createJoinCode();

        // 각각의 emailList[i]의 값을 inviteMemberDTO에 넣기
//        InviteMemberDTO inviteMemberDTO = new InviteMemberDTO();
//        inviteMemberDTO.setJoinCode(createJoinCode());

//        System.out.println("데이터 타입" + data.get("email").getClass().getName());
//        System.out.println(data.get("email"));
//        List<String> emailList = (List<String>) data.get("email");

//        ArrayList<String> emailList = data.get("email")
//
//
//
////        String emailList = data.get("email").toString();
//        System.out.println("이메일리스트" + emailList.getClass().getName());
//        System.out.println(emailList);
//
//
//        for(int i = 0; i < emailList.toArray().length; i++){
//            inviteMemberDTO.setEmailList(emailList);
//        }
//        System.out.println(inviteMemberDTO.getEmailList());
//
        return mv;
    }

    // 프로젝트 참가 코드 발생 함수 -> 팀장님이 작성하신 코드 참조(코드 길이만 변형)
    private String createJoinCode(){
        // 33 ~ 38 : 특수 기호(! " # $ % &)
        // 48 ~ 57 : 숫자(0 1 2 3 4 5 6 7 8 9)
        // 65 ~ 90 : 영문자 대문자
        // 97 ~ 122 : 영문자 소문자
        int min = 33;
//        int min = 48;
        int max = 122; //
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

    @GetMapping(value="selectproject", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public ProjectDTO selectSpecificProject(ModelAndView mv, @ModelAttribute ProjectDTO projectDTO){
        projectDTO = projectService.getProject(projectDTO);
        mv.addObject("select", projectService.getProject(projectDTO));
        System.out.println("select/update = " + projectDTO);
        mv.setViewName("/project/projectmain");
        return projectDTO;

    }

    @PostMapping("/updateproject")
    public ModelAndView updateProject(ModelAndView mv, @ModelAttribute ProjectDTO projectDTO){
        projectService.updateProject(projectDTO);
        mv.addObject("projectList", projectService.getList());
        System.out.println("update" + projectDTO);
        mv.setViewName("redirect:/project/projectmain");
        return mv;
    }

    @PostMapping("/deleteproject")
    public ModelAndView deleteProject(ModelAndView mv, @ModelAttribute ProjectDTO projectDTO){
        projectService.deleteProject(projectDTO);
        mv.addObject("projectList", projectService.getList());
        System.out.println("delete" + projectDTO);
        mv.setViewName("redirect:/project/projectmain");
        return mv;
    }


}
