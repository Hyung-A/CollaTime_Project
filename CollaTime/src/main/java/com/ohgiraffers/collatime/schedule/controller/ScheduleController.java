package com.ohgiraffers.collatime.schedule.controller;

import com.ohgiraffers.collatime.schedule.model.dto.ScheduleDTO;
import com.ohgiraffers.collatime.schedule.model.service.ScheduleService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;




@Controller // HTML 뷰를 반환하기 위한 컨트롤러
public class ScheduleController {

    private final ScheduleService scheduleService;

    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    // 스케줄 메인 화면을 반환 (http://127.0.0.1:8003/schedule)
    @GetMapping("/schedule")
    public String showCalendar() {
        // templates 폴더의 calendar.html을 반환
        return "schedule/calendar";
    }

    // 모든 일정 조회 (GET /api/schedules/all)
    @GetMapping("/api/schedules/all")
    @ResponseBody // JSON 데이터를 반환
    public ResponseEntity<List<ScheduleDTO>> getAllSchedules() {
        List<ScheduleDTO> schedules = scheduleService.getAllSchedules();
        return ResponseEntity.ok(schedules);
    }

    // 특정 일정 조회 (GET /api/schedules/{id})
    @GetMapping("/api/schedules/{id}")
    @ResponseBody // JSON 데이터를 반환
    public ResponseEntity<ScheduleDTO> getSchedule(@PathVariable("id") int scheduleNo) {
        ScheduleDTO schedule = scheduleService.getScheduleById(scheduleNo);
        return ResponseEntity.ok(schedule);
    }

    // 일정 추가 (POST /api/schedules)
    @PostMapping("/api/schedules")
    @ResponseBody // JSON 데이터를 반환
    public ResponseEntity<Void> insertSchedule(@RequestBody ScheduleDTO schedule) {
        scheduleService.insertSchedule(schedule);
        return ResponseEntity.ok().build();
    }

    // 일정 수정 (PUT /api/schedules/{id})
    @PutMapping("/api/schedules/{id}")
    @ResponseBody // JSON 데이터를 반환
    public ResponseEntity<Void> updateSchedule(@PathVariable("id") int scheduleNo, @RequestBody ScheduleDTO schedule) {
        schedule.setScheduleNo(scheduleNo);
        scheduleService.updateSchedule(schedule);
        return ResponseEntity.ok().build();
    }

    // 일정 삭제 (DELETE /api/schedules/{id})
    @DeleteMapping("/api/schedules/{id}")
    @ResponseBody // JSON 데이터를 반환
    public ResponseEntity<Void> deleteSchedule(@PathVariable("id") int scheduleNo) {
        scheduleService.deleteSchedule(scheduleNo);
        return ResponseEntity.noContent().build();
    }
}

