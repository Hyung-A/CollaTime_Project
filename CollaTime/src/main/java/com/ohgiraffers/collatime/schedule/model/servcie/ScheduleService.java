package com.ohgiraffers.collatime.schedule.model.service;


import com.ohgiraffers.collatime.project.model.dto.ProjectDTO;
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

    public List<ScheduleDTO> getList(){
        return scheduleMapper.getList();
    }

    public ScheduleDTO getSchedule() {
        return scheduleMapper.getSchedule();
    }

    @Transactional
    public void insertSchedule(ScheduleDTO scheduleDTO){
        scheduleMapper.insertSchedule(scheduleDTO);
//         taskMapper.insertTask(projectDTO.getTask()); // 예외 발생 시 모든 작업 취소
    }

}
