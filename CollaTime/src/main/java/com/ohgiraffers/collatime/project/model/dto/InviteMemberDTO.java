package com.ohgiraffers.collatime.project.model.dto;

import java.util.List;

public class InviteMemberDTO {

    private String projectNo;
    private int userNo;
    private String joinCode;
    private String email;
    private String nickName;

    public InviteMemberDTO(){}

    public InviteMemberDTO(String projectNo, int userNo, String joinCode, String email, String nickName) {
        this.projectNo = projectNo;
        this.userNo = userNo;
        this.joinCode = joinCode;
        this.email = email;
        this.nickName = nickName;

    }

    public String getProjectNo() {
        return projectNo;
    }

    public void setProjectNo(String projectNo) {
        this.projectNo = projectNo;
    }

    public int getUserNo() {
        return userNo;
    }

    public void setUserNo(int userNo) {
        this.userNo = userNo;
    }

    public String getJoinCode() {
        return joinCode;
    }

    public void setJoinCode(String joinCode) {
        this.joinCode = joinCode;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    @Override
    public String toString() {
        return "InviteMemberDTO{" +
                "projectNo='" + projectNo + '\'' +
                ", userNo=" + userNo +
                ", joinCode='" + joinCode + '\'' +
                ", email='" + email + '\'' +
                ", nickName='" + nickName + '\'' +
                '}';
    }
}
