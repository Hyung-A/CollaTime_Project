package com.ohgiraffers.collatime.mail;

public class MailDTO {

    private String mail;
    private String code;

    public MailDTO() {}

    public MailDTO(String mail, String code) {
        this.mail = mail;
        this.code = code;
    }

    @Override
    public String toString() {
        return "MailDTO{" +
                "mail='" + mail + '\'' +
                ", code='" + code + '\'' +
                '}';
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
