package com.ohgiraffers.collatime.schedule.model.dto;


public class ScheduleDTO {


    private int scheduleNo;
    private int projectNo;
    private String scheduleStartDate;
    private String scheduleEndDate;
    private String scheduleContent; // 메모
    private String scheduleTitle; // 제목
    private String scheduleCreator;
    private String colorCode;

    private String textColorCode;

    private Integer[]  scheduleNoList;

    public ScheduleDTO() {}

    public ScheduleDTO(int scheduleNo, int projectNo, String scheduleStartDate, String scheduleEndDate, String scheduleContent, String scheduleTitle, String scheduleCreator, String colorCode) {
        this.scheduleNo = scheduleNo;
        this.projectNo = projectNo;
        this.scheduleStartDate = scheduleStartDate;
        this.scheduleEndDate = scheduleEndDate;
        this.scheduleContent = scheduleContent;
        this.scheduleTitle = scheduleTitle;
        this.scheduleCreator = scheduleCreator;
        this.colorCode = colorCode;
    }

    public int getScheduleNo() {
        return scheduleNo;
    }

    public void setScheduleNo(int scheduleNo) {
        this.scheduleNo = scheduleNo;
    }

    public int getProjectNo() {
        return projectNo;
    }

    public void setProjectNo(int projectNo) {
        this.projectNo = projectNo;
    }

    public String getScheduleStartDate() {
        return scheduleStartDate;
    }

    public void setScheduleStartDate(String scheduleStartDate) {
        this.scheduleStartDate = scheduleStartDate;
    }

    public String getScheduleEndDate() {
        return scheduleEndDate;
    }

    public void setScheduleEndDate(String scheduleEndDate) {
        this.scheduleEndDate = scheduleEndDate;
    }

    public String getScheduleContent() {
        return scheduleContent;
    }

    public void setScheduleContent(String scheduleContent) {
        this.scheduleContent = scheduleContent;
    }

    public String getScheduleTitle() {
        return scheduleTitle;
    }

    public void setScheduleTitle(String scheduleTitle) {
        this.scheduleTitle = scheduleTitle;
    }

    public String getScheduleCreator() {
        return scheduleCreator;
    }

    public void setScheduleCreator(String scheduleCreator) {
        this.scheduleCreator = scheduleCreator;
    }

    public String getColorCode() {
        return colorCode;
    }

    public void setColorCode(String colorCode) {
        this.colorCode = colorCode;
    }

    public String getTextColorCode() {
        return textColorCode;
    }

    public void setTextColorCode(String textColorCode) {
        this.textColorCode = textColorCode;
    }

    public Integer[] getScheduleNoList() {
        return scheduleNoList;
    }

    public void setScheduleNoList(Integer[] scheduleNoList) {
        this.scheduleNoList = scheduleNoList;
    }

    @Override
    public String toString() {
        return "ScheduleDTO{" +
                "scheduleNo=" + scheduleNo +
                ", projectNo=" + projectNo +
                ", scheduleStartDate='" + scheduleStartDate + '\'' +
                ", scheduleEndDate='" + scheduleEndDate + '\'' +
                ", scheduleContent='" + scheduleContent + '\'' +
                ", scheduleTitle='" + scheduleTitle + '\'' +
                ", scheduleCreator='" + scheduleCreator + '\'' +
                ", colorCode='" + colorCode + '\'' +
                ", textColorCode='" + textColorCode + '\'' +
                ", scheduleNoList=" + scheduleNoList +
                '}';
    }
}