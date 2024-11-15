package com.ohgiraffers.collatime.admin.model.service;

import com.ohgiraffers.collatime.admin.model.dao.AdminProjectMapper;
import com.ohgiraffers.collatime.admin.model.dao.AdminUserMapper;
import com.ohgiraffers.collatime.admin.model.dao.AdminVisitMapper;
import com.ohgiraffers.collatime.admin.model.dto.AdminProjectDTO;
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

    private AdminProjectMapper adminProjectMapper;
    @Autowired
    public AdminService(AdminVisitMapper adminVisitMapper, AdminUserMapper adminUserMapper, AdminProjectMapper adminProjectMapper) {
        this.adminVisitMapper = adminVisitMapper;
        this.adminUserMapper = adminUserMapper;
        this.adminProjectMapper = adminProjectMapper;
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

    public List<UserDTO> searchUserById(String userId) {
        return adminUserMapper.searchUserById(userId);
    }

    public List<UserDTO> searchUserByEmail(String userEmail) {
        return adminUserMapper.searchUserByEmail(userEmail);
    }

    public List<UserDTO> searchUserByName(String userName) {
        return adminUserMapper.searchUserByName(userName);
    }

    public List<UserDTO> searchUserByNickname(String userNickname) {
        return adminUserMapper.searchUserByNickname(userNickname);
    }

    public int deleteUserOk(int userNo) {
        return adminUserMapper.deleteUserOk(userNo);
    }

    public List<AdminProjectDTO> allActiveProject() {
        return adminProjectMapper.allActiveProject();
    }

    public int deleteAllDeactive(List<Integer> list) {
        return adminProjectMapper.deleteAllDeactive(list);
    }
}
