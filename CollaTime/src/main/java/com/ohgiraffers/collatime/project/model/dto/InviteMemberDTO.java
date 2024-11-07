package com.ohgiraffers.collatime.project.model.dto;

public class InviteMemberDTO {

    String email;

    public InviteMemberDTO(){}

    public InviteMemberDTO(String email) {
        this.email = email;
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
                "email='" + email + '\'' +
                '}';
    }
}
