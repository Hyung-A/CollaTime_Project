package com.ohgiraffers.collatime.schedule.model.dto;

public class joinScheduleDTO {

    private int scheduleNo;
    private int userNo;

    public joinScheduleDTO() {}

    public joinScheduleDTO(int scheduleNo, int userNo) {
        this.scheduleNo = scheduleNo;
        this.userNo = userNo;
    }

    public int getScheduleNo() {
        return scheduleNo;
    }

    public void setScheduleNo(int scheduleNo) {
        this.scheduleNo = scheduleNo;
    }

    public int getUserNo() {
        return userNo;
    }

    public void setUserNo(int userNo) {
        this.userNo = userNo;
    }

    @Override
    public String toString() {
        return "joinScheduleDTO{" +
                "scheduleNo=" + scheduleNo +
                ", userNo=" + userNo +
                '}';
    }
}
