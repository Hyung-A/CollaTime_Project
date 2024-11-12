package com.ohgiraffers.collatime.project.model.dto;

import java.util.List;

public class ManageMemberDTO {

    // productor = 1명, member = 여러명, productor != member
    private String projectNo;
    private int productorNo;
    private String productorNickname;
    private String productorEmail;
    private List<MemberListDTO> memberList;
    private String note;

    public ManageMemberDTO(){}

    public ManageMemberDTO(String projectNo, int productorNo, String productorNickname, String productorEmail, List<MemberListDTO> memberList, String note) {
        this.projectNo = projectNo;
        this.productorNo = productorNo;
        this.productorNickname = productorNickname;
        this.productorEmail = productorEmail;
        this.memberList = memberList;
        this.note = note;
    }

    public String getProjectNo() {
        return projectNo;
    }

    public void setProjectNo(String projectNo) {
        this.projectNo = projectNo;
    }

    public int getProductorNo() {
        return productorNo;
    }

    public void setProductorNo(int productorNo) {
        this.productorNo = productorNo;
    }

    public String getProductorNickname() {
        return productorNickname;
    }

    public void setProductorNickname(String productorNickname) {
        this.productorNickname = productorNickname;
    }

    public String getProductorEmail() {
        return productorEmail;
    }

    public void setProductorEmail(String productorEmail) {
        this.productorEmail = productorEmail;
    }

    public List<MemberListDTO> getMemberList() {
        return memberList;
    }

    public void setMemberList(List<MemberListDTO> memberList) {
        this.memberList = memberList;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "ManageMemberDTO{" +
                "projectNo='" + projectNo + '\'' +
                ", productorNo=" + productorNo +
                ", productorNickname='" + productorNickname + '\'' +
                ", productorEmail='" + productorEmail + '\'' +
                ", memberList=" + memberList +
                ", note='" + note + '\'' +
                '}';
    }
}
