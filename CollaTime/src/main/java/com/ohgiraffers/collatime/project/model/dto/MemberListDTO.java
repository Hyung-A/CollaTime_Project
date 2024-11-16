package com.ohgiraffers.collatime.project.model.dto;

public class MemberListDTO {

    private String memberNo;
    private String memberNickname;
    private String memberEmail;
    private String isProductor;

    public MemberListDTO(){}

    public MemberListDTO(String memberNo, String memberNickname, String memberEmail, String isProductor) {
        this.memberNo = memberNo;
        this.memberNickname = memberNickname;
        this.memberEmail = memberEmail;
        this.isProductor = isProductor;
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

    public String getIsProductor() {
        return isProductor;
    }

    public void setIsProductor(String isProductor) {
        this.isProductor = isProductor;
    }

    @Override
    public String toString() {
        return "MemberListDTO{" +
                "memberNo='" + memberNo + '\'' +
                ", memberNickname='" + memberNickname + '\'' +
                ", memberEmail='" + memberEmail + '\'' +
                ", isProductor='" + isProductor + '\'' +
                '}';
    }
}
