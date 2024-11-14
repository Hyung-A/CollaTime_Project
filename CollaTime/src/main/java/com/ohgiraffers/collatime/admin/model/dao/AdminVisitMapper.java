package com.ohgiraffers.collatime.admin.model.dao;

import com.ohgiraffers.collatime.admin.model.dto.AdminVisitDTO;
import org.apache.ibatis.annotations.Mapper;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Mapper
public interface AdminVisitMapper {
    List<AdminVisitDTO> weekMaker(LocalDate thisMonday, LocalDate thisSunday);

    List<Map<Integer, Integer>> monthMaker();
}
