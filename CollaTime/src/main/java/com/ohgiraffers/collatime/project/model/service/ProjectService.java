package com.ohgiraffers.collatime.project.model.service;

import com.ohgiraffers.collatime.project.model.dao.ProjectMapper;
import com.ohgiraffers.collatime.project.model.dto.InviteMemberDTO;
import com.ohgiraffers.collatime.project.model.dto.MemberListDTO;
import com.ohgiraffers.collatime.project.model.dto.ProjectDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProjectService {
    private final ProjectMapper projectMapper;

    // 기본 생성자
    public ProjectService(ProjectMapper projectMapper){
        this.projectMapper = projectMapper;
    }

    // 프로젝트 리스트 전체 조회
    public List<ProjectDTO> getList(int userNo) {
        return projectMapper.getList(userNo);
    }

    // 프로젝트 데이터 조회
    public ProjectDTO getProject(ProjectDTO projectDTO) {
        return projectMapper.getProject(projectDTO);
    }

    // 프로젝트 생성 - Project DB에 데이터 입력
    @Transactional
    public void insertProject(ProjectDTO projectDTO) {
        projectMapper.insertProject(projectDTO);
    }

    // 프로젝트 생성 - JoinProject DB에 데이터 입력
    @Transactional
    public void insertJoinProject(InviteMemberDTO inviteMemberDTO) {
        projectMapper.insertJoinProject(inviteMemberDTO);
    }

    // 프로젝트 마지막 번호 조회
    public int getLastProjectNo() {
        return projectMapper.getLastProjectNo();
    }

    // 프로젝트 수정
    @Transactional
    public void updateProject(ProjectDTO projectDTO){
        projectMapper.updateProject(projectDTO);
    }

    // 프로젝트 삭제
    @Transactional
    public void deleteProject(ProjectDTO projectDTO) {
        projectMapper.deleteProject(projectDTO);
    }

    // join_project 내용도 삭제
    @Transactional
    public void deleteMember(InviteMemberDTO inviteMemberDTO) {
        projectMapper.deleteMember(inviteMemberDTO);
    }

    // 프로젝트 수정 - join_project 내용 수정
    @Transactional
    public void updateMember(InviteMemberDTO inviteMemberDTO) {
        projectMapper.updateMember(inviteMemberDTO);
    }

    public List<InviteMemberDTO> getInviteMemberList(ProjectDTO projectDTO) {
        return projectMapper.getInviteMemberList(projectDTO);
    }

    // joinCode 전체 조회
    public List<InviteMemberDTO> getJoinCodeList() {
        return projectMapper.getJoinCodeList();
    }

    // 참가코드 입력 시 joinPoject의 ct_user_no랑 email 수정
    @Transactional
    public void updateUserNo(InviteMemberDTO inviteMemberDTO) {
        projectMapper.updateUserNo(inviteMemberDTO);
    }


    public List<MemberListDTO> getMemberManager(ProjectDTO projectDTO) {
        return projectMapper.getMemberManager(projectDTO);
    }
}
