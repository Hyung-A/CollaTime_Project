package com.ohgiraffers.collatime.user.model.dto;

public class SignupDTO implements java.io.Serializable{

    private String userId;
    private String userPwd;
    private String userName;
    private String userNickname;
    private String userEmail;

    public SignupDTO(String userId, String userPwd, String userName, String userNickname, String userEmail) {
        this.userId = userId;
        this.userPwd = userPwd;
        this.userName = userName;
        this.userNickname = userNickname;
        this.userEmail = userEmail;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserNickname() {
        return userNickname;
    }

    public void setUserNickname(String userNickname) {
        this.userNickname = userNickname;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    @Override
    public String toString() {
        return "SignupDTO{" +
                "userId='" + userId + '\'' +
                ", userPwd='" + userPwd + '\'' +
                ", userName='" + userName + '\'' +
                ", userNickname='" + userNickname + '\'' +
                ", userEmail='" + userEmail + '\'' +
                '}';
    }
}