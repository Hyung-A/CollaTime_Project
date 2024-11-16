package com.ohgiraffers.collatime.inquiry.controller;

import com.ohgiraffers.collatime.auth.model.AuthDetails;
import com.ohgiraffers.collatime.inquiry.model.dto.InquiryDTO;
import com.ohgiraffers.collatime.inquiry.model.service.InquiryService;
import com.ohgiraffers.collatime.project.model.dto.ProjectDTO;
import com.ohgiraffers.collatime.project.model.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/inquiry")
public class InquiryController {


    private final InquiryService inquiryService;
    private final ProjectService projectService;

    @Autowired
    public InquiryController(InquiryService inquiryService, ProjectService projectService){
        this.inquiryService = inquiryService;
        this.projectService = projectService;
    }

    @PostMapping("/registinquiry")
    public ModelAndView registinquiry(ModelAndView mv, @AuthenticationPrincipal AuthDetails authDetails, @ModelAttribute InquiryDTO inquiryDTO){
        inquiryDTO.setUserNo(authDetails.getUserNo());

        int result = inquiryService.registInquiry(inquiryDTO);
        String message = "";

        if (result > 0) {
            message = "문의가 성공적으로 전달되었습니다.";

        } else {
            message = "문의에 실패하였습니다..";
        }

        mv.addObject("message", message);


        mv.setViewName("/common/resultinquirymodal");
        return mv;
    }

    @GetMapping(value = "/myinquirylist", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public List<InquiryDTO> myinquiry(@AuthenticationPrincipal AuthDetails authDetails){

        inquiryService.myInquiryList(authDetails.getUserNo()).forEach(System.out::println);
        List<InquiryDTO> inquiryDTOList = inquiryService.myInquiryList(authDetails.getUserNo());


        return inquiryDTOList;
    }

    @GetMapping("/myinquiryinfo/{inquiryNo}")
    public ModelAndView myinquiryinfo(ModelAndView mv, @PathVariable("inquiryNo") int inquiryNo){
        InquiryDTO inquiryDTO = inquiryService.inquiryInfoByNo(inquiryNo);
        mv.addObject("inquiryinfo", inquiryDTO);
        mv.setViewName("/mypage/myinquiryinfo");
        return mv;
    }

    @PostMapping("/passauth")
    public ModelAndView passauth(ModelAndView mv, @AuthenticationPrincipal AuthDetails authDetails ,@RequestParam int passProjectNo, @RequestParam String passUserNickname){
        ProjectDTO projectDTO = new ProjectDTO();
        projectDTO.setProjectNo(String.valueOf(passProjectNo));
        ProjectDTO projectDTO1 = projectService.getProject(projectDTO);
        String passProjectName = projectDTO1.getProjectName();
        InquiryDTO inquiryDTO = new InquiryDTO();
        inquiryDTO.setInquiryStatus("권한처리중");
        inquiryDTO.setUserNo(authDetails.getUserNo());
        String passAuthContext = authDetails.getUserNickname()+"의 "+ passProjectName+" 프로젝트 권한을 "+passUserNickname+"에게 위임 요청합니다.";
        inquiryDTO.setInquiryTitle(authDetails.getUserNickname()+"의 권한 위임 요청");
        inquiryDTO.setInquiryContent(passAuthContext);
        inquiryDTO.setProjectNo(passProjectNo);
        inquiryDTO.setPassAuthName(passUserNickname);

        int result = inquiryService.registPassAuthInquiry(inquiryDTO);
        String message = "";

        if (result > 0) {
            message = "권한 요청이 성공적으로 전달되었습니다.";

        } else {
            message = "문의에 실패하였습니다..";
        }

        mv.addObject("message", message);


        mv.setViewName("/common/resultinquirymodal");
        return mv;
    }

    @GetMapping(value = "/userReadInquiry/{inquiryNo}", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public List<String> userReadInquiry(@PathVariable("inquiryNo") int inquiryNo){

        List<String> message = new ArrayList<>();

        int result = inquiryService.userReadInquiry(inquiryNo);

        if(result>0){
            message.add("문의를 읽으셨습니다.");
        }else {
            message.add("문의를 읽음 변환 실패.");
        }
        return message;
    }


    @GetMapping(value = "/productorOfProject", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public List<ProjectDTO> productorOfProject(@AuthenticationPrincipal AuthDetails authDetails){
        List<ProjectDTO> projectList = projectService.productorOfProject(authDetails.getUserNo());
        return projectList;
    }
}
