package com.ohgiraffers.collatime.schedule.model.service;

import com.ohgiraffers.collatime.schedule.model.dao.ScheduleMapper;
import com.ohgiraffers.collatime.schedule.model.dto.ScheduleDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ScheduleService {

    private final ScheduleMapper scheduleMapper;


    public ScheduleService(ScheduleMapper scheduleMapper) {
        this.scheduleMapper = scheduleMapper;
    }

    // 모든 일정 조회 (Read)
    public List<ScheduleDTO> getAllSchedules(){
        return scheduleMapper.getList();
    }

    // 특정 일정 조회 (Read by ID)
    public ScheduleDTO getScheduleById(int scheduleNo) {
        return scheduleMapper.getSchedule(scheduleNo);
    }

    @Transactional
    public void insertSchedule(ScheduleDTO schedule) {
        scheduleMapper.insertSchedule(schedule);
    }

    // 일정 수정 (Update)
    public void updateSchedule(ScheduleDTO schedule) {
        scheduleMapper.updateSchedule(schedule);
    }

    // 일정 삭제 (Delete)
    public void deleteSchedule(int scheduleNo) {
        scheduleMapper.deleteSchedule(scheduleNo);
    }

}
