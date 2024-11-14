package com.ohgiraffers.collatime.admin.model.service;

import com.ohgiraffers.collatime.admin.model.dao.AdminVisitMapper;
import com.ohgiraffers.collatime.admin.model.dto.AdminVisitDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Service
public class AdminService {

    private final AdminVisitMapper adminVisitMapper;

    @Autowired
    public AdminService(AdminVisitMapper adminVisitMapper) {
        this.adminVisitMapper = adminVisitMapper;
    }

    public List<AdminVisitDTO> weekMaker(LocalDate thisMonday, LocalDate thisSunday) {
        return adminVisitMapper.weekMaker(thisMonday, thisSunday);
    }

    public List<Map<Integer, Integer>> monthMaker() {
        return adminVisitMapper.monthMaker();
    }
}
