package com.ohgiraffers.collatime.project.model.dto;

import java.util.List;

public class InviteMemberDTO {

    private String projectNo;
    private String joinCode;
    private String email;

    public InviteMemberDTO(){}

    public InviteMemberDTO(String projectNo, String joinCode, String email) {
        this.projectNo = projectNo;
        this.joinCode = joinCode;
        this.email = email;
    }

    public String getProjectNo() {
        return projectNo;
    }

    public void setProjectNo(String projectNo) {
        this.projectNo = projectNo;
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

    @Override
    public String toString() {
        return "InviteMemberDTO{" +
                "projectNo=" + projectNo +
                ", joinCode='" + joinCode + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
