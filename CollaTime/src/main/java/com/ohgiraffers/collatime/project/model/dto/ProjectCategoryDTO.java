package com.ohgiraffers.collatime.project.model.dto;

public class ProjectCategoryDTO {

    private String projectCategorycode;
    private String projectCategoryName;

    public ProjectCategoryDTO(){}

    public ProjectCategoryDTO(String projectCategorycode, String projectCategoryName) {
        this.projectCategorycode = projectCategorycode;
        this.projectCategoryName = projectCategoryName;
    }

    public String getProjectCategorycode() {
        return projectCategorycode;
    }

    public void setProjectCategorycode(String projectCategorycode) {
        this.projectCategorycode = projectCategorycode;
    }

    public String getProjectCategoryName() {
        return projectCategoryName;
    }

    public void setProjectCategoryName(String projectCategoryName) {
        this.projectCategoryName = projectCategoryName;
    }

    @Override
    public String toString() {
        return "ProjectCategoryDTO{" +
                "projectCategorycode='" + projectCategorycode + '\'' +
                ", projectCategoryName='" + projectCategoryName + '\'' +
                '}';
    }
}
