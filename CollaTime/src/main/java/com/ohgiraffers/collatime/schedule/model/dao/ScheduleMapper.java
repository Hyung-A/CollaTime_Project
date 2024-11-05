package com.ohgiraffers.collatime.schedule.model.dao;

import com.ohgiraffers.collatime.schedule.model.dto.ScheduleDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ScheduleMapper {

    List<ScheduleDTO> getList();

    ScheduleDTO getSchedule();

    void insertSchedule(ScheduleDTO scheduleDTO);
}
