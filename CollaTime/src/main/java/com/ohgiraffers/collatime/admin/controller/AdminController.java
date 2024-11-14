package com.ohgiraffers.collatime.admin.controller;

import com.ohgiraffers.collatime.admin.model.dto.AdminVisitDTO;
import com.ohgiraffers.collatime.admin.model.service.AdminService;
import com.ohgiraffers.collatime.user.model.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
                    System.out.println("compareThisWeekFor = " + compareThisWeek.get(i));
                    dailyUsing.add(thisWeek.get(j).getVisitCount());
                }else {
                    check++;
                }
            }
            System.out.println(check);
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
        System.out.println("collaUsingMonth = " + collaUsingMonth);
        System.out.println("달이 잘 나오나 "+collaUsingMonth.get(4).get("month"));
        System.out.println("카운트가 잘 나오나 "+collaUsingMonth.get(4).get("count"));
        System.out.println("collaUsingMonth 사이즈가 얼마고 = " + collaUsingMonth.size());

        List<Integer> compareMonth = new ArrayList<>();
        for (int i = 1; i < 13; i++){
            compareMonth.add(i);
        }

        for(int i = 0; i < 12; i++) {
            int check = 0;
            for (int j = 0; j < collaUsingMonth.size(); j++) {
                if (compareMonth.get(i) == collaUsingMonth.get(j).get("month")) {
                    System.out.println("compareThisWeekFor = " + compareMonth.get(i));
                    monthlyUsing.add(Integer.parseInt(String.valueOf(collaUsingMonth.get(j).get("count"))));
                }else {
                    check++;
                }
            }
            System.out.println(check);
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
}
