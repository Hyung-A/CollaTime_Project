package com.ohgiraffers.collatime.schedule.model.service;

import com.ohgiraffers.collatime.schedule.model.dao.ScheduleMapper;
import com.ohgiraffers.collatime.schedule.model.dto.ScheduleDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service
public class ScheduleService {

    private final ScheduleMapper scheduleMapper;


    public ScheduleService(ScheduleMapper scheduleMapper) {
        this.scheduleMapper = scheduleMapper;
    }


    // 특정 일정 조회 (Read by ID)
    public ScheduleDTO getScheduleById(int scheduleNo) {
        return scheduleMapper.getSchedule(scheduleNo);
    }

    @Transactional
    public void insertSchedule(ScheduleDTO schedule) {

        scheduleMapper.insertSchedule(schedule);

        Integer[] participantNos = schedule.getParticipantNos();
        if(participantNos != null) {
            Map<String, Object> map = new HashMap<>();
            map.put("scheduleNo", schedule.getScheduleNo());
            //해당 스케즐 참가자 초기화
            scheduleMapper.deleteParticipant(map);

            for (Integer participantNo : participantNos) {
                map.put("participantNo", participantNo);
                scheduleMapper.insertParticipant(map);
            }
        }
    }



    // 일정 수정 (Update)
    @Transactional
    public void updateSchedule(ScheduleDTO schedule) {
        scheduleMapper.updateSchedule(schedule);

        Integer[] participantNos = schedule.getParticipantNos();
        if(participantNos != null) {
            Map<String, Object> map = new HashMap<>();
            map.put("scheduleNo", schedule.getScheduleNo());
            //해당 스케즐 참가자 초기화
            scheduleMapper.deleteParticipant(map);

            for (Integer participantNo : participantNos) {
                map.put("participantNo", participantNo);
                scheduleMapper.insertParticipant(map);
            }
        }
    }

    // 일정 삭제 (Delete)
    public void deleteSchedule(int scheduleNo) {
        scheduleMapper.deleteSchedule(scheduleNo);
    }

    public List<ScheduleDTO> getUserSchedules(String username,String projectNo) {
        Map<String, Object> map = new HashMap<>();
        map.put("username", username);
        map.put("projectNo", projectNo);
        return scheduleMapper.getUserSchedules(map);
    }

    public List<Map<String, Object>> participantList(Integer projectNo) {
        return scheduleMapper.participantList(projectNo);
    }


    //스케쥴 목록 가져올 때 참가자 목록도 불러오기
    public List<ScheduleDTO> getScheduleNoList(ScheduleDTO scheduleDTO) {
        return scheduleMapper.getScheduleNoList(scheduleDTO).stream()
                .peek(schedule->schedule.setParticipantList(scheduleParticipantList(schedule.getScheduleNo())))
                .collect(Collectors.toList());
    }


    public List<Map<String, Object>> scheduleParticipantList(Integer scheduleNo) {
        return  scheduleMapper.scheduleParticipantList( scheduleNo);
    }

}
