package com.ohgiraffers.collatime.project.model.dto;

import java.util.Date;

public class ProjectDTO {

    /* newProjectDTO */

    private int projectNo;
    private String projectName;
    private String projectSummary;
    private String projectPurpose;
    private String startDate;
    private String endDate;
    private String projectCategoryCode;

    public ProjectDTO() {}

    public ProjectDTO(int projectNo, String projectName, String projectSummary, String projectPurpose, String startDate, String endDate, String projectCategoryCode) {
        this.projectName = projectName;
        this.projectSummary = projectSummary;
        this.projectPurpose = projectPurpose;
        this.startDate = startDate;
        this.endDate = endDate;
        this.projectCategoryCode = projectCategoryCode;
    }

    public int getProjectNo(){
        return projectNo;
    }

    public void setProjectNo(int projectNo){
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

    @Override
    public String toString() {
        return "ProjectDTO{" +
                "projectName='" + projectName + '\'' +
                ", projectSummary='" + projectSummary + '\'' +
                ", projectPurpose='" + projectPurpose + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", projectCategoryCode='" + projectCategoryCode + '\'' +
                '}';
    }
}