package com.ohgiraffers.collatime.project.model.dto;

import java.util.List;

public class InviteMemberDTO {

    private int projectNo;
    private String joinCode;
    private List<String> emailList;

    public InviteMemberDTO(){}

    public InviteMemberDTO(int projectNo, String joinCode, List<String> emailList) {
        this.projectNo = projectNo;
        this.joinCode = joinCode;
        this.emailList = emailList;
    }

    public InviteMemberDTO(String joinCode, List<String> emailList) {
        this.joinCode = joinCode;
        this.emailList = emailList;
    }

    public int getProjectNo() {
        return projectNo;
    }

    public void setProjectNo(int projectNo) {
        this.projectNo = projectNo;
    }

    public String getJoinCode() {
        return joinCode;
    }

    public void setJoinCode(String joinCode) {
        this.joinCode = joinCode;
    }

    public List<String> getEmailList() {
        return emailList;
    }

    public void setEmailList(List<String> emailList) {
        this.emailList = emailList;
    }

    @Override
    public String toString() {
        return "InviteMemberDTO{" +
                "projectNo=" + projectNo +
                ", joinCode='" + joinCode + '\'' +
                ", emailList=" + emailList +
                '}';
    }
}
