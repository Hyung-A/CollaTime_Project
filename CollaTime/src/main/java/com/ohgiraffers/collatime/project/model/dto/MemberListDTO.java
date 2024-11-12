package com.ohgiraffers.collatime.project.model.dto;

public class MemberListDTO {

    private String memberNo;
    private String memberNickname;
    private String memberEmail;

    public MemberListDTO(){}

    public MemberListDTO(String memberNo, String memberNickname, String memberEmail) {
        this.memberNo = memberNo;
        this.memberNickname = memberNickname;
        this.memberEmail = memberEmail;
    }

    public String getMemberNo() {
        return memberNo;
    }

    public void setMemberNo(String memberNo) {
        this.memberNo = memberNo;
    }

    public String getMemberNickname() {
        return memberNickname;
    }

    public void setMemberNickname(String memberNickname) {
        this.memberNickname = memberNickname;
    }

    public String getMemberEmail() {
        return memberEmail;
    }

    public void setMemberEmail(String memberEmail) {
        this.memberEmail = memberEmail;
    }

    @Override
    public String toString() {
        return "MemberListDTO{" +
                "memberNo='" + memberNo + '\'' +
                ", memberNickname='" + memberNickname + '\'' +
                ", memberEmail='" + memberEmail + '\'' +
                '}';
    }
}
