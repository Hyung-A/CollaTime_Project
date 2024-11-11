package com.ohgiraffers.collatime.schedule.model.dao;

import com.ohgiraffers.collatime.schedule.model.dto.ScheduleDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ScheduleMapper {

    // 로그인한 사용자 모든 일정 조회
    List<ScheduleDTO> getUserSchedules(String username);

    // 특정 일정 조회
    // ScheduleDTO getSchedule();

    // 특정 일정 조회 - ID를 기준으로 조회
    ScheduleDTO getSchedule(int scheduleNo); // 특정 일정 조회 시 ID 전달

    // 일정 추가
    void insertSchedule(ScheduleDTO scheduleDTO);

    // 일정 수정
    void updateSchedule(ScheduleDTO scheduleDTO);

    // 일정 삭제
    void deleteSchedule(int scheduleNo); // ID를 통해 삭제



}
