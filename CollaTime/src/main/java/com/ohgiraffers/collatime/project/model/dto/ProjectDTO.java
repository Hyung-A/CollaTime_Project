package com.ohgiraffers.collatime.project.model.dto;

import java.util.Date;
import java.util.List;

public class ProjectDTO {

    /* newProjectDTO */

    private String projectNo;
    private String projectName;
    private String projectSummary;
    private String projectPurpose;
    private String startDate;
    private String endDate;
    private String projectCategoryCode;
    private List<String> emailList;
    private List<InviteMemberDTO> inviteMemberList;
    private int productorNo;

    public ProjectDTO() {}

    public ProjectDTO(String projectNo, String projectName, String projectSummary, String projectPurpose, String startDate, String endDate, String projectCategoryCode, List<String> emailList, List<InviteMemberDTO> inviteMemberList, int productorNo) {
        this.projectNo = projectNo;
        this.projectName = projectName;
        this.projectSummary = projectSummary;
        this.projectPurpose = projectPurpose;
        this.startDate = startDate;
        this.endDate = endDate;
        this.projectCategoryCode = projectCategoryCode;
        this.emailList = emailList;
        this.inviteMemberList = inviteMemberList;
        this.productorNo = productorNo;
    }

    public String getProjectNo() {
        return projectNo;
    }

    public void setProjectNo(String projectNo) {
        this.projectNo = projectNo;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectSummary() {
        return projectSummary;
    }

    public void setProjectSummary(String projectSummary) {
        this.projectSummary = projectSummary;
    }

    public String getProjectPurpose() {
        return projectPurpose;
    }

    public void setProjectPurpose(String projectPurpose) {
        this.projectPurpose = projectPurpose;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getProjectCategoryCode() {
        return projectCategoryCode;
    }

    public void setProjectCategoryCode(String projectCategoryCode) {
        this.projectCategoryCode = projectCategoryCode;
    }

    public List<String> getEmailList() {
        return emailList;
    }

    public void setEmailList(List<String> emailList) {
        this.emailList = emailList;
    }

    public List<InviteMemberDTO> getInviteMemberList() {
        return inviteMemberList;
    }

    public void setInviteMemberList(List<InviteMemberDTO> inviteMemberList) {
        this.inviteMemberList = inviteMemberList;
    }

    public int getProductorNo() {
        return productorNo;
    }

    public void setProductorNo(int productorNo) {
        this.productorNo = productorNo;
    }

    @Override
    public String toString() {
        return "ProjectDTO{" +
                "projectNo='" + projectNo + '\'' +
                ", projectName='" + projectName + '\'' +
                ", projectSummary='" + projectSummary + '\'' +
                ", projectPurpose='" + projectPurpose + '\'' +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", projectCategoryCode='" + projectCategoryCode + '\'' +
                ", emailList=" + emailList +
                ", inviteMemberList=" + inviteMemberList +
                ", productorNo=" + productorNo +
                '}';
    }
}