package com.ohgiraffers.collatime.inquiry.controller;

import com.ohgiraffers.collatime.auth.model.AuthDetails;
import com.ohgiraffers.collatime.inquiry.model.dto.InquiryDTO;
import com.ohgiraffers.collatime.inquiry.model.service.InquiryService;
import com.ohgiraffers.collatime.project.model.dto.ProjectDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/inquiry")
public class InquiryController {


    private final InquiryService inquiryService;

    @Autowired
    public InquiryController(InquiryService inquiryService){
        this.inquiryService = inquiryService;
    }

    @PostMapping("/registinquiry")
    public ModelAndView registinquiry(ModelAndView mv, @AuthenticationPrincipal AuthDetails authDetails, @ModelAttribute InquiryDTO inquiryDTO){
        inquiryDTO.setUserNo(authDetails.getUserNo());
        System.out.println("문의 신청 왔다.");
        System.out.println(inquiryDTO);

        int result = inquiryService.registInquiry(inquiryDTO);
        String message = "";

        if (result > 0) {
            message = "문의가 성공적으로 전달되었습니다.";
            System.out.println("yes");

        } else {
            message = "문의에 실패하였습니다..";
            System.out.println("no");
        }

        mv.addObject("message", message);


        mv.setViewName("/common/resultinquirymodal");
        return mv;
    }

    @GetMapping(value = "/myinquirylist", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public List<InquiryDTO> myinquiry(@AuthenticationPrincipal AuthDetails authDetails){

        System.out.println("inquiryList");
        System.out.println(authDetails.getUserNo());
        inquiryService.myInquiryList(authDetails.getUserNo()).forEach(System.out::println);
        List<InquiryDTO> inquiryDTOList = inquiryService.myInquiryList(authDetails.getUserNo());


        return inquiryDTOList;
    }

    @GetMapping("/myinquiryinfo/{inquiryNo}")
    public ModelAndView myinquiryinfo(ModelAndView mv, @PathVariable("inquiryNo") int inquiryNo){
        System.out.println(inquiryNo);
        InquiryDTO inquiryDTO = inquiryService.inquiryInfoByNo(inquiryNo);
        mv.addObject("inquiryinfo", inquiryDTO);
        mv.setViewName("/mypage/myinquiryinfo");
        return mv;
    }

//    @GetMapping(value = "/havingproject", produces = "application/json; charset=UTF-8")
//    @ResponseBody
//    public List<ProjectDTO> havingproject(@AuthenticationPrincipal AuthDetails authDetails){
//
//        System.out.println(authDetails.getUserNo());
//        List<ProjectDTO> projectDTOList = projectService.;
//
//
//
//        return projectDTOList;
//    }
}
