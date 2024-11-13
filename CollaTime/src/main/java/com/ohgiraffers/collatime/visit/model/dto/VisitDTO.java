package com.ohgiraffers.collatime.visit.model.dto;

import java.time.LocalDate;

public class VisitDTO {

    private LocalDate visitDate;
    private int visitCount;

    public VisitDTO(){}

    public VisitDTO(LocalDate visitDate, int visitCount) {
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
