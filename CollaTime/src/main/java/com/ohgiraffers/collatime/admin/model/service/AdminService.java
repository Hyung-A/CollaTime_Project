package com.ohgiraffers.collatime.admin.model.service;

import com.ohgiraffers.collatime.admin.model.dao.AdminUserMapper;
import com.ohgiraffers.collatime.admin.model.dao.AdminVisitMapper;
import com.ohgiraffers.collatime.admin.model.dto.AdminVisitDTO;
import com.ohgiraffers.collatime.user.model.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Service
public class AdminService {

    private AdminVisitMapper adminVisitMapper;

    private AdminUserMapper adminUserMapper;

    @Autowired
    public AdminService(AdminVisitMapper adminVisitMapper, AdminUserMapper adminUserMapper) {
        this.adminVisitMapper = adminVisitMapper;
        this.adminUserMapper = adminUserMapper;
    }

    public List<AdminVisitDTO> weekMaker(LocalDate thisMonday, LocalDate thisSunday) {
        return adminVisitMapper.weekMaker(thisMonday, thisSunday);
    }

    public List<Map<Integer, Integer>> monthMaker() {
        return adminVisitMapper.monthMaker();
    }

    public List<UserDTO> searchAllUser() {
        System.out.println("서비스 까지도 왔다..");
        return adminUserMapper.searchAllUser();
    }

    public List<UserDTO> searchUserByNo(int userNo) {
        return adminUserMapper.searchUserByNo(userNo);
    }
}
