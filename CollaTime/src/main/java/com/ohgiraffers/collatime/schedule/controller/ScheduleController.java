package com.ohgiraffers.collatime.schedule.controller;

import com.ohgiraffers.collatime.schedule.model.service.ScheduleService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/schedule")
public class ScheduleController {

    private final ScheduleService scheduleService;

    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @GetMapping({"", "/schedulemain"})
    public String scheduleMain(){

        return "schedule/calendar";
    }

//    @GetMapping("/createSchedule")
//    public void createSchedule(){}
}

