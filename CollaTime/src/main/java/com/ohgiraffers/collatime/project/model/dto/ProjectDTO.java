package com.ohgiraffers.collatime.project.model.dto;

import java.util.Date;

public class ProjectDTO {

    /* newProjectDTO */

    private String projectName;
    private String projectSummary;
    private String projectPurpose;
    private Date startDate;
    private Date endDate;
    private ProjectCategoryDTO projectCategoryCode;

    public ProjectDTO() {
        projectCategoryCode = new ProjectCategoryDTO();
    }

    public ProjectDTO(String projectName, String projectSummary, String projectPurpose, Date startDate, Date endDate, ProjectCategoryDTO projectCategoryCode) {
        this.projectName = projectName;
        this.projectSummary = projectSummary;
        this.projectPurpose = projectPurpose;
        this.startDate = startDate;
        this.endDate = endDate;
        this.projectCategoryCode = projectCategoryCode;
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

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public ProjectCategoryDTO getProjectCategoryCode() {
        return projectCategoryCode;
    }

    public void setProjectCategoryCode(ProjectCategoryDTO projectCategoryCode) {
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
                ", projectCategoryCode=" + projectCategoryCode +
                '}';
    }
}