package com.ohgiraffers.collatime.admin.model.service;

import com.ohgiraffers.collatime.admin.model.dao.AdminInquiryMapper;
import com.ohgiraffers.collatime.admin.model.dao.AdminProjectMapper;
import com.ohgiraffers.collatime.admin.model.dao.AdminUserMapper;
import com.ohgiraffers.collatime.admin.model.dao.AdminVisitMapper;
import com.ohgiraffers.collatime.admin.model.dto.AdminProjectDTO;
import com.ohgiraffers.collatime.admin.model.dto.AdminVisitDTO;
import com.ohgiraffers.collatime.inquiry.model.dto.InquiryDTO;
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

    private AdminInquiryMapper adminInquiryMapper;
    @Autowired
    public AdminService(AdminVisitMapper adminVisitMapper, AdminUserMapper adminUserMapper, AdminProjectMapper adminProjectMapper, AdminInquiryMapper adminInquiryMapper) {
        this.adminVisitMapper = adminVisitMapper;
        this.adminUserMapper = adminUserMapper;
        this.adminProjectMapper = adminProjectMapper;
        this.adminInquiryMapper = adminInquiryMapper;
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

    public List<AdminProjectDTO> searchProjectByNo(int projectNo) { return adminProjectMapper.searchProjectByNo(projectNo);
    }

    public List<AdminProjectDTO> searchProjectByName(String projectName) { return adminProjectMapper.searchProjectByName(projectName);
    }

    public List<AdminProjectDTO> searchProjectByEndDate(String projectEndDate) { return adminProjectMapper.searchProjectByEndDate(projectEndDate);
    }

    public List<AdminProjectDTO> searchProjectByProductorNo(int productorNo) { return adminProjectMapper.searchProjectByProductorNo(productorNo);
    }

    public List<InquiryDTO> passAuth() {
        return adminInquiryMapper.passAuth();
    }

    public List<InquiryDTO> require() { return adminInquiryMapper.require();
    }

    public List<InquiryDTO> answer() { return adminInquiryMapper.answer();
    }

    public List<InquiryDTO> read() { return adminInquiryMapper.read();
    }

    public InquiryDTO searchInquiry(int inquiryNo) { return adminInquiryMapper.searchInquiry(inquiryNo);
    }

    public int authPassUser(InquiryDTO inquiryDTO) { return adminProjectMapper.authPassUser(inquiryDTO);
    }

    public int inquiryUpdateStatus(InquiryDTO inquiryDTO) { return adminInquiryMapper.inquiryUpdateStatus(inquiryDTO);
    }

    public int addAnswer(int inquiryNo, String answerContent) { return adminInquiryMapper.addAnswer(inquiryNo, answerContent);
    }
}
