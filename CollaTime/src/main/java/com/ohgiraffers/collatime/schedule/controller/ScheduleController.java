package com.ohgiraffers.collatime.schedule.controller;

import com.ohgiraffers.collatime.schedule.model.dto.ScheduleDTO;
import com.ohgiraffers.collatime.schedule.model.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;




@Controller // HTML 뷰를 반환하기 위한 컨트롤러
@RequestMapping
public class ScheduleController {

    private final ScheduleService scheduleService;

    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @GetMapping(produces = "application/json; charset=UTF-8")
    public String showCalendar(Principal principal, Model model) {
        // Principal 객체를 통해 현재 로그인된 사용자의 정보를 가져옴
        // principal.getName()은 로그인한 사용자의 이름(또는 ID)을 반환함
        String creatorName = principal.getName();  // 로그인된 사용자의 이름
        model.addAttribute("creatorName", creatorName);
        List<ScheduleDTO> schedules = scheduleService.getUserSchedules(creatorName);
        model.addAttribute("schedules", schedules);
        return "schedule/calendar";
    }

    // 모든 일정 조회 (GET /schedule)
    @GetMapping(produces = "application/json; charset=UTF-8")
    @ResponseBody
    public List<ScheduleDTO> getAllSchedules(Principal principal) {
        String username = principal.getName();
        return scheduleService.getUserSchedules(username);
    }

    // 일정 생성 (POST /schedule)
    @PostMapping(produces = "application/json; charset=UTF-8")
    @ResponseBody
    public ResponseEntity<String> createSchedule(@RequestBody ScheduleDTO schedule) {
        scheduleService.insertSchedule(schedule);
        return ResponseEntity.ok("일정이 추가되었습니다.");
    }

    // 일정 수정 (PUT /schedule)
    @PutMapping(produces = "application/json; charset=UTF-8")
    @ResponseBody
    public ResponseEntity<String> updateSchedule(@RequestBody ScheduleDTO schedule) {
        scheduleService.updateSchedule(schedule);
        return ResponseEntity.ok("일정이 수정되었습니다.");
    }

    // 일정 삭제 (DELETE /schedule?id=1)
    @DeleteMapping(produces = "application/json; charset=UTF-8")
    @ResponseBody
    public ResponseEntity<String> deleteSchedule(@RequestParam("id") int scheduleNo) {
        scheduleService.deleteSchedule(scheduleNo);
        return ResponseEntity.ok("일정이 삭제되었습니다.");
    }

    // 특정 일정 조회 (GET /schedule?id=1)
    @GetMapping(params = "id", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public ScheduleDTO getSchedule(@RequestParam("id") int scheduleNo) {
        return scheduleService.getScheduleById(scheduleNo);
    }

}

