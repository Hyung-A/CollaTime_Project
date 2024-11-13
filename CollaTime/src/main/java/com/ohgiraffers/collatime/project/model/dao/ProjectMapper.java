package com.ohgiraffers.collatime.project.model.dao;

import com.ohgiraffers.collatime.project.model.dto.InviteMemberDTO;
import com.ohgiraffers.collatime.project.model.dto.MemberListDTO;
import com.ohgiraffers.collatime.project.model.dto.ProjectDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProjectMapper {
    /* ProjectMapper */

    // 프로젝트 리스트 전체 조회
    List<ProjectDTO> getList(int userNo);

    // 프로젝트 데이터 조회
    ProjectDTO getProject(ProjectDTO projectDTO);

    // 프로젝트 생성 - Project DB에 데이터 입력
    void insertProject(ProjectDTO projectDTO);

    // 프로젝트 생성 - JoinProject DB에 데이터 입력
    void insertJoinProject(InviteMemberDTO inviteMemberDTO);

    // 프로젝트 마지막 번호 조회
    int getLastProjectNo();

    // 프로젝트 수정 - project DB
    void updateProject(ProjectDTO projectDTO);

    // 프로젝트 삭제 - project DB
    void deleteProject(ProjectDTO projectDTO);

    // 프로젝트 삭제 - join-project DB
    void deleteMember(InviteMemberDTO inviteMemberDTO);

    // 프로젝트 수정 - join_project DB
    void updateMember(InviteMemberDTO inviteMemberDTO);

    List<InviteMemberDTO> getInviteMemberList(ProjectDTO projectDTO);

    // 프로젝트 참가 코드 전체 조회
    List<InviteMemberDTO> getJoinCodeList();

    // 참가코드 입력 시 joinPoject의 ct_user_no랑 email 수정
    void updateUserNo(InviteMemberDTO inviteMemberDTO);

    // 멤버 관리 모달에서 쓸 팀원 정보 조회
    List<MemberListDTO> getMemberManager(ProjectDTO projectDTO);

    void deleteMemberInfo(InviteMemberDTO inviteMemberDTO);
}
