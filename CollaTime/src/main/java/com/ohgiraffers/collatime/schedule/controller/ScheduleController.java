package com.ohgiraffers.collatime.schedule.controller;

import com.ohgiraffers.collatime.project.model.dto.MemberListDTO;
import com.ohgiraffers.collatime.project.model.dto.ProjectDTO;
import com.ohgiraffers.collatime.project.model.service.ProjectService;
import com.ohgiraffers.collatime.schedule.model.dto.ScheduleDTO;
import com.ohgiraffers.collatime.schedule.model.service.ScheduleService;
import com.ohgiraffers.collatime.user.model.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;


@Controller // HTML 뷰를 반환하기 위한 컨트롤러
public class ScheduleController {

    private final ScheduleService scheduleService;
    private final ProjectService projectService;


    @Autowired
    public ScheduleController(ScheduleService scheduleService,ProjectService  projectService) {
        this.scheduleService = scheduleService;
        this.projectService = projectService;
    }



    @GetMapping("/schedule")
    public String showCalendar(Principal principal, @RequestParam(value = "projectNo")   Integer projectNo,  Model model) {
        // Principal 객체를 통해 현재 로그인된 사용자의 정보를 가져옴
        // principal.getName()은 로그인한 사용자의 이름(또는 ID)을 반환함

        String username = principal.getName(); // 현재 로그인한 사용자 이름 가져옴

        ScheduleDTO schedule =null;
        if(projectNo != null) {
            schedule = scheduleService.getScheduleById(projectNo);
        }

        if (schedule != null) {
            model.addAttribute("schedule", schedule);
        } else {
            model.addAttribute("schedule", new ScheduleDTO()); // 빈 리스트로 초기화
        }

        String creatorName = principal.getName();  // 로그인된 사용자의 이름
        model.addAttribute("creatorName", creatorName);
        return "schedule/calendar";
    }

    // 모든 일정 조회 (GET /api/schedules/all)
    @GetMapping("/api/schedules/user/{projectNo}")
    @ResponseBody // JSON 데이터를 반환
    public ResponseEntity<List<ScheduleDTO>> getUserSchedules(Principal principal ,
                                                              @PathVariable("projectNo") String projectNo) {
    
        String username = principal.getName(); // 현재 로그인한 사용자 이름 가져옴
        List<ScheduleDTO> schedules = scheduleService.getUserSchedules(username, projectNo);
        return ResponseEntity.ok(schedules);
    }

    // 특정 일정 조회 (GET /api/schedules/{id})
    @GetMapping("/api/schedules/{scheduleNo}")
    @ResponseBody // JSON 데이터를 반환
    public ResponseEntity<ScheduleDTO> getSchedule(@PathVariable("scheduleNo") int scheduleNo) {
        ScheduleDTO schedule = scheduleService.getScheduleById(scheduleNo);
        return ResponseEntity.ok(schedule);
    }


    //특정일 스케쥴 목록 불러오기
    @PostMapping("/api/schedules/scheduleNoList")
    @ResponseBody // JSON 데이터를 반환
    public ResponseEntity<?> getScheduleNoList(@RequestBody ScheduleDTO scheduleDTO) {
        System.out.println("scheduleDTO  :" + Arrays.toString(scheduleDTO.getScheduleNoList()));
        List<ScheduleDTO> schedule = scheduleService.getScheduleNoList(scheduleDTO);
        return ResponseEntity.ok(schedule);
    }




    // 일정 추가 (POST /api/schedules)
    @PostMapping("/api/schedules")
    @ResponseBody // JSON 데이터를 반환
    public ResponseEntity<Void> insertSchedule(@RequestBody ScheduleDTO schedule) {
        // 1. 클라이언트가 보낸 JSON 데이터를 ScheduleDTO 객체로 변환하여 받음.
        scheduleService.insertSchedule(schedule);
        // 2. scheduleService.insertSchedule(schedule) 호출을 통해 서비스 계층에 저장 요청을 보냄.
        return ResponseEntity.ok().build();
        // 3. 저장이 성공적으로 완료되면 클라이언트에게 200 OK 응답을 반환합니다.
    }



    // 일정 수정 (PUT /api/schedules/{id})
    @PutMapping("/api/schedules/{id}")
    @ResponseBody // JSON 데이터를 반환
    public ResponseEntity<Void> updateSchedule(@PathVariable("id") int scheduleNo, Principal principal,
                                               @RequestBody ScheduleDTO schedule) {
        String username = principal.getName();
        schedule.setScheduleCreator(username);

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

    @GetMapping("/schedule/details/{scheduleNo}")
    public String getScheduleDetails(@PathVariable("scheduleNo") int scheduleNo, Model model) {
        // scheduleNo에 맞는 스케줄 정보를 DB에서 가져오기
        ScheduleDTO schedule = scheduleService.getScheduleById(scheduleNo);

        // 데이터를 모델에 추가
        model.addAttribute("scheduleTitle", schedule.getScheduleTitle());
        model.addAttribute("scheduleStartDate", schedule.getScheduleStartDate());
        model.addAttribute("scheduleEndDate", schedule.getScheduleEndDate());
        model.addAttribute("memo", schedule.getScheduleContent());
        model.addAttribute("colorCode", schedule.getColorCode());
        model.addAttribute("creatorName", schedule.getScheduleCreator());

        //스케줄 참가자 목록 가져오기
        List<Map<String, Object>> participantList = scheduleService.scheduleParticipantList(scheduleNo);
        model.addAttribute("participantList", participantList);

        return "schedule/details"; // 해당 데이터를 가지고 details.html로 이동
    }


    /**
     *  http://localhost:8003/api/schedule/participant/1
     * 스케쥴 프로젝트 참가자 목록 가져오기
     * @param projectNo
     * @param model
     * @return
     */
    @GetMapping("/api/schedule/participant/{projectNo}")
    @ResponseBody
    public ResponseEntity<?> participantList(@PathVariable("projectNo") String projectNo, ProjectDTO projectDTO, Model model) {
        try {
            projectDTO.setProjectNo(projectNo);
            List<MemberListDTO> memberList = projectService.getMemberManager(projectDTO);
            return ResponseEntity.ok(memberList);
        }catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    /** http://localhost:8003/api/schedule/participantList/2
     * 스케줄 참가자 목록
     * @param scheduleNo
     * @return
     */
    @GetMapping("/api/schedule/participantList/{scheduleNo}")
    @ResponseBody
    public ResponseEntity<?> scheduleParticipantList(@PathVariable("scheduleNo") Integer scheduleNo) {
        try {
            List<Map<String, Object>> participantList = scheduleService.scheduleParticipantList(scheduleNo);
            return ResponseEntity.ok(participantList);
        }catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }




}

