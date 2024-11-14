package com.ohgiraffers.collatime.admin.model.dto;

public class AdminProjectDTO {
    private int projectNo;
    private String projectName;
    private String projectEndDate;
    private String productorNo;

    public AdminProjectDTO() {}

    public AdminProjectDTO(int projectNo, String projectName, String projectEndDate, String productorNo) {
        this.projectNo = projectNo;
        this.projectName = projectName;
        this.projectEndDate = projectEndDate;
        this.productorNo = productorNo;
    }

    @Override
    public String toString() {
        return "AdminProjectDTO{" +
                "projectNo=" + projectNo +
                ", projectName='" + projectName + '\'' +
                ", projectEndDate='" + projectEndDate + '\'' +
                ", productorNo='" + productorNo + '\'' +
                '}';
    }

    public int getProjectNo() {
        return projectNo;
    }

    public void setProjectNo(int projectNo) {
        this.projectNo = projectNo;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectEndDate() {
        return projectEndDate;
    }

    public void setProjectEndDate(String projectEndDate) {
        this.projectEndDate = projectEndDate;
    }

    public String getProductorNo() {
        return productorNo;
    }

    public void setProductorNo(String productorNo) {
        this.productorNo = productorNo;
    }
}
