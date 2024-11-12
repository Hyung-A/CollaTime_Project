package com.ohgiraffers.collatime.inquiry.model.dto;

public class InquiryDTO {

    private int inquiryNo;
    private String inquiryStatus;
    private String inquiryTitle;
    private String inquiryContent;
    private int userNo;
    private String answerContent;

    public InquiryDTO(){}

    public InquiryDTO(int inquiryNo, String inquiryStatus, String inquiryTitle, String inquiryContent, int userNo, String answerContent) {
        this.inquiryNo = inquiryNo;
        this.inquiryStatus = inquiryStatus;
        this.inquiryTitle = inquiryTitle;
        this.inquiryContent = inquiryContent;
        this.userNo = userNo;
        this.answerContent = answerContent;
    }

    public int getInquiryNo() {
        return inquiryNo;
    }

    public void setInquiryNo(int inquiryNo) {
        this.inquiryNo = inquiryNo;
    }

    public String getInquiryStatus() {
        return inquiryStatus;
    }

    public void setInquiryStatus(String inquiryStatus) {
        this.inquiryStatus = inquiryStatus;
    }

    public String getInquiryTitle() {
        return inquiryTitle;
    }

    public void setInquiryTitle(String inquiryTitle) {
        this.inquiryTitle = inquiryTitle;
    }

    public String getInquiryContent() {
        return inquiryContent;
    }

    public void setInquiryContent(String inquiryContent) {
        this.inquiryContent = inquiryContent;
    }

    public int getUserNo() {
        return userNo;
    }

    public void setUserNo(int userNo) {
        this.userNo = userNo;
    }

    public String getAnswerContent() {
        return answerContent;
    }

    public void setAnswerContent(String answerContent) {
        this.answerContent = answerContent;
    }

    @Override
    public String toString() {
        return "InquiryDTO{" +
                "inquiryNo=" + inquiryNo +
                ", inquiryStatus='" + inquiryStatus + '\'' +
                ", inquiryTitle='" + inquiryTitle + '\'' +
                ", inquiryContent='" + inquiryContent + '\'' +
                ", userNo=" + userNo +
                ", answerContent='" + answerContent + '\'' +
                '}';
    }
}
