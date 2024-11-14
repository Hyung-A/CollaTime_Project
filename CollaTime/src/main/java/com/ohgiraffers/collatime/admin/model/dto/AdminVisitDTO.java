package com.ohgiraffers.collatime.admin.model.dto;

import java.time.LocalDate;

public class AdminVisitDTO {

    private LocalDate visitDate;
    private int visitCount;

    public AdminVisitDTO(){}

    public AdminVisitDTO(LocalDate visitDate, int visitCount) {
        this.visitDate = visitDate;
        this.visitCount = visitCount;
    }

    public LocalDate getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(LocalDate visitDate) {
        this.visitDate = visitDate;
    }

    public int getVisitCount() {
        return visitCount;
    }

    public void setVisitCount(int visitCount) {
        this.visitCount = visitCount;
    }

    @Override
    public String toString() {
        return "VisitDTO{" +
                "visitDate=" + visitDate +
                ", visitCount=" + visitCount +
                '}';
    }
}
