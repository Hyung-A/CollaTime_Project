package com.ohgiraffers.collatime.visit.model.dao;

import com.ohgiraffers.collatime.visit.model.dto.VisitDTO;
import org.apache.ibatis.annotations.Mapper;

import java.time.LocalDate;

@Mapper
public interface VisitMapper {
    VisitDTO visitDate(LocalDate date);

    void todayLoginUser(VisitDTO visitUser);

    void newDateLoginUser(VisitDTO visitUser);
}
