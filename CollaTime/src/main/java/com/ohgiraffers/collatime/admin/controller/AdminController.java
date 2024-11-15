package com.ohgiraffers.collatime.admin.controller;

import com.ohgiraffers.collatime.admin.model.dto.AdminProjectDTO;
import com.ohgiraffers.collatime.admin.model.dto.AdminVisitDTO;
import com.ohgiraffers.collatime.admin.model.service.AdminService;
import com.ohgiraffers.collatime.inquiry.model.dto.InquiryDTO;
import com.ohgiraffers.collatime.project.model.dto.ProjectDTO;
import com.ohgiraffers.collatime.user.model.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService){
        this.adminService = adminService;
    }

    @GetMapping("/dashboard")
    public void dashboard(){}

    @GetMapping(value = "/weekmaker", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public List<Integer> weekmaker(){
        List<AdminVisitDTO> thisWeek = new ArrayList<>();
        LocalDate now = LocalDate.now();
        LocalDate thisMonday = now.with(DayOfWeek.MONDAY);
        LocalDate thisSunday = now.with(DayOfWeek.SUNDAY);

        List<LocalDate> compareThisWeek = new ArrayList<>();
        List<Integer> dailyUsing = new ArrayList<>();

        compareThisWeek.add(thisMonday);
        for(int i = 1; i < 7; i++){
            compareThisWeek.add(thisMonday.plusDays(i));
        }

        thisWeek = adminService.weekMaker(thisMonday, thisSunday);

        for(int i = 0; i < 7; i++) {
            int check = 0;
            for (int j = 0; j < thisWeek.size(); j++) {
                if (compareThisWeek.get(i).equals(thisWeek.get(j).getVisitDate())) {
                    dailyUsing.add(thisWeek.get(j).getVisitCount());
                }else {
                    check++;
                }
            }
            if(check==thisWeek.size()){
                dailyUsing.add(0);
            }
        }


        return dailyUsing;
    }

    @GetMapping(value = "/monthmaker", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public List<Integer> monthmaker(){

        List<Integer> monthlyUsing = new ArrayList<>();

        List<Map<Integer, Integer>> collaUsingMonth = adminService.monthMaker();

        List<Integer> compareMonth = new ArrayList<>();
        for (int i = 1; i < 13; i++){
            compareMonth.add(i);
        }

        for(int i = 0; i < 12; i++) {
            int check = 0;
            for (int j = 0; j < collaUsingMonth.size(); j++) {
                if (compareMonth.get(i) == collaUsingMonth.get(j).get("month")) {
                    monthlyUsing.add(Integer.parseInt(String.valueOf(collaUsingMonth.get(j).get("count"))));
                }else {
                    check++;
                }
            }
            if(check==collaUsingMonth.size()){
                monthlyUsing.add(0);
            }
        }

        return monthlyUsing;
    }

    @GetMapping("/user")
    public void user(){}

    @GetMapping(value = "/alluser",  produces = "application/json; charset=UTF-8")
    @ResponseBody
    public List<UserDTO> alluser(){
        System.out.println("여까지 왔다.");

        return adminService.searchAllUser();
    }

    @GetMapping(value = "/user/number/{userNo}",  produces = "application/json; charset=UTF-8")
    @ResponseBody
    public List<UserDTO> searchUserByNo(@PathVariable("userNo") int userNo){
        System.out.println("여까지 왔다.");

        return adminService.searchUserByNo(userNo);
    }

    @GetMapping(value = "/user/id/{userId}",  produces = "application/json; charset=UTF-8")
    @ResponseBody
    public List<UserDTO> searchUserById(@PathVariable("userId") String userId){
        System.out.println("id 여까지 왔다.");

        return adminService.searchUserById(userId);
    }

    @GetMapping(value = "/user/email/{userEmail}",  produces = "application/json; charset=UTF-8")
    @ResponseBody
    public List<UserDTO> searchUserByEmail(@PathVariable("userEmail") String userEmail){
        System.out.println("id 여까지 왔다.");

        return adminService.searchUserByEmail(userEmail);
    }

    @GetMapping(value = "/user/name/{userName}",  produces = "application/json; charset=UTF-8")
    @ResponseBody
    public List<UserDTO> searchUserByName(@PathVariable("userName") String userName){
        System.out.println("id 여까지 왔다.");

        return adminService.searchUserByName(userName);
    }

    @GetMapping(value = "/user/nickname/{userNickname}",  produces = "application/json; charset=UTF-8")
    @ResponseBody
    public List<UserDTO> searchUserByNickname(@PathVariable("userNickname") String userNickname){
        System.out.println("id 여까지 왔다.");

        return adminService.searchUserByNickname(userNickname);
    }

    @GetMapping("/deleteuser/{userNo}")
    public ModelAndView deleteUser(ModelAndView mv,@PathVariable("userNo") int userNo){
        UserDTO userDTO = adminService.searchUserByNo(userNo).get(0);
        System.out.println(userDTO);

        String message = "";

        message = userDTO.getUserNo()+"번 회원의 정보를 삭제하시겠습니까?";

        mv.addObject("user", userDTO);
        mv.addObject("message", message);
        mv.setViewName("admin/deleteuser");

        return mv;
    }

    @GetMapping( "/deleteresult/{deleteUserNo}")
    public ModelAndView deleteuserok(ModelAndView mv, @PathVariable("deleteUserNo") int deleteUserNo){
        System.out.println("왔당");
        int result = adminService.deleteUserOk(deleteUserNo);
        String message = "";

        if(result>0){
            message = "삭제가 완료되었습니다.";
        }else {
            message = "삭제에 실패했습니다.";
        }

        mv.addObject("message",message);
        mv.setViewName("admin/deleteresult");
        return mv;
    }

//    프로젝트
    @GetMapping("/project")
    public void project(){}

    @GetMapping(value = "/allActiveProject",  produces = "application/json; charset=UTF-8")
    @ResponseBody
    public List<AdminProjectDTO> allActiveProject(){
        LocalDate today = LocalDate.now();
        LocalDate activeDay = today.plusDays(14);
//        List<AdminProjectDTO> a = adminService.allActiveProject(activeDay);
        List<AdminProjectDTO> activeProject = compareEndDate(1);

        System.out.println("activeProject = " + activeProject);
        return activeProject;
    }
    @GetMapping(value = "/allDeactiveProject",  produces = "application/json; charset=UTF-8")
    @ResponseBody
    public List<AdminProjectDTO> allDeactiveProject(){
        List<AdminProjectDTO> deativeProject = compareEndDate(2);
        System.out.println("deativeProject = " + deativeProject);
        return deativeProject;
    }

    private List<AdminProjectDTO> compareEndDate(int compare){
        LocalDate today = LocalDate.now();
        LocalDate activeDay = today.minusDays(14);
        List<AdminProjectDTO> project = adminService.allActiveProject();
        List<AdminProjectDTO> ActiveProject = new ArrayList<>();
        List<AdminProjectDTO> DeactiveProject = new ArrayList<>();

        for (int i = 0; i < project.size(); i++ ) {
            LocalDate projectDate = LocalDate.parse(project.get(i).getProjectEndDate());
            if(activeDay.isAfter(projectDate)){
                DeactiveProject.add(project.get(i));
            }else {
                ActiveProject.add(project.get(i));
            }
        }

        if (compare == 1){
            return ActiveProject;
        }else{
            return DeactiveProject;
        }
    }

    @GetMapping("/deleteproject")
    public ModelAndView deleteproject(ModelAndView mv){
        String message = "";

        message = "일괄삭제를 진행하시겠습니까?";
        mv.addObject("message", message);
        mv.setViewName("admin/deleteproject");
        return mv;
    }

    @GetMapping(value = "/deleteAllDeactive",  produces = "application/json; charset=UTF-8")
    @ResponseBody
    public List<String> deleteAllDeactive(){
        List<AdminProjectDTO> deativeProject = compareEndDate(2);
        List<Integer> deleteProjectList = new ArrayList<>();
        List<String> message = new ArrayList<>();
        if(deativeProject.size()>0) {
            for (int i = 0; i < deativeProject.size(); i++) {
                deleteProjectList.add(deativeProject.get(i).getProjectNo());
            }
            int result = adminService.deleteAllDeactive(deleteProjectList);


            if (result > 0) {
                message.add("일괄삭제가 완료되었습니다.");
            } else {
                message.add("삭제에 실패했습니다.");
            }

        }else {
            message.add("데이터가 없습니다.");
        }
        return message;
    }

    @GetMapping(value = "/project/number/{projectNo}",  produces = "application/json; charset=UTF-8")
    @ResponseBody
    public List<AdminProjectDTO> searchProjectByNo(@PathVariable("projectNo")int projectNo){
        return adminService.searchProjectByNo(projectNo);
    }

    @GetMapping(value = "/project/name/{projectName}",  produces = "application/json; charset=UTF-8")
    @ResponseBody
    public List<AdminProjectDTO> searchProjectByName(@PathVariable("projectName")String projectName){
        return adminService.searchProjectByName(projectName);
    }

    @GetMapping(value = "/project/enddate/{projectEndDate}",  produces = "application/json; charset=UTF-8")
    @ResponseBody
    public List<AdminProjectDTO> searchProjectByEndDate(@PathVariable("projectEndDate") String projectEndDate){
        return adminService.searchProjectByEndDate(projectEndDate);
    }

    @GetMapping(value = "/project/productorno/{productorNo}",  produces = "application/json; charset=UTF-8")
    @ResponseBody
    public List<AdminProjectDTO> searchProjectByProductorNo(@PathVariable("productorNo") int productorNo){
        return adminService.searchProjectByProductorNo(productorNo);
    }

//    문의
    @GetMapping("/inquiry")
    public void inquiry(){}

    @GetMapping(value = "/PassAuth",  produces = "application/json; charset=UTF-8")
    @ResponseBody
    public List<InquiryDTO> passAuth(){
        return adminService.passAuth();
    }

    @GetMapping(value = "/Require",  produces = "application/json; charset=UTF-8")
    @ResponseBody
    public List<InquiryDTO> require(){
        return adminService.require();
    }

    @GetMapping(value = "/Answer",  produces = "application/json; charset=UTF-8")
    @ResponseBody
    public List<InquiryDTO> answer(){
        return adminService.answer();
    }

    @GetMapping(value = "/Read",  produces = "application/json; charset=UTF-8")
    @ResponseBody
    public List<InquiryDTO> read(){
        return adminService.read();
    }

    @GetMapping("/authpass/{inquiryNo}")
    public ModelAndView doPassAuth(ModelAndView mv, @PathVariable("inquiryNo") int inquiryNo){

        System.out.println("권한 위임 하이");
        InquiryDTO inquiryDTO = adminService.searchInquiry(inquiryNo);
        String message = "";
        int resultAuthPass = adminService.authPassUser(inquiryDTO);
        System.out.println("resultAuthPass = " + resultAuthPass);

        if(resultAuthPass>0) {
            int resultUpdate = adminService.inquiryUpdateStatus(inquiryDTO);
            if(resultUpdate>0){
                message = "답변에 성공했습니다.";
            }else {
                message = "답변에 실패했습니다.";
            }
        }else {
            message = "수정에 실패했습니다.";
        }
        mv.addObject("message", message);
        mv.setViewName("/admin/inquiry");
        return mv;
    }
}

