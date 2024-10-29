package com.ohgiraffers.collatime.domain.user.model.dto;

public class UserDTO {

    private int userNo;
    private String userId;
    private String userPwd;
    private String userEmail;
    private String userName;
    private String userNickname;
    private String userPicture;
    private String userColor;
    private String userRole;

    public UserDTO(int userNo, String userId, String userPwd, String userEmail, String userName, String userNickname, String userPicture, String userColor, String userRole) {
        this.userNo = userNo;
        this.userId = userId;
        this.userPwd = userPwd;
        this.userEmail = userEmail;
        this.userName = userName;
        this.userNickname = userNickname;
        this.userPicture = userPicture;
        this.userColor = userColor;
        this.userRole = userRole;
    }

    public int getUserNo() {
        return userNo;
    }

    public void setUserNo(int userNo) {
        this.userNo = userNo;
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

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
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

    public String getUserPicture() {
        return userPicture;
    }

    public void setUserPicture(String userPicture) {
        this.userPicture = userPicture;
    }

    public String getUserColor() {
        return userColor;
    }

    public void setUserColor(String userColor) {
        this.userColor = userColor;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }


}

