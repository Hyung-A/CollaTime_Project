package com.ohgiraffers.collatime.user.model.dto;

import com.ohgiraffers.collatime.common.UserRole;
import org.apache.catalina.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UserDTO implements java.io.Serializable{

    private int userNo;
    private String userId;
    private String password;
    private String userEmail;
    private String userName;
    private String userNickname;
    private String userPicture;
    private String userColor;
    private UserRole userRole;

    public UserDTO(){}

    public List<String> getRole(){
        if(this.userRole.getRole().length()>0){
            return Arrays.asList(this.userRole.getRole().split(","));
        }

        return new ArrayList<>();
    }

    public UserDTO(int userNo, String userId, String userPwd, String userEmail, String userName, String userNickname, String userPicture, String userColor, UserRole userRole) {
        this.userNo = userNo;
        this.userId = userId;
        this.password = userPwd;
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
        return password;
    }

    public void setUserPwd(String userPwd) {
        this.password = userPwd;
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

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "userNo=" + userNo +
                ", userId='" + userId + '\'' +
                ", password='" + password + '\'' +
                ", userEmail='" + userEmail + '\'' +
                ", userName='" + userName + '\'' +
                ", userNickname='" + userNickname + '\'' +
                ", userPicture='" + userPicture + '\'' +
                ", userColor='" + userColor + '\'' +
                ", userRole=" + userRole +
                '}';
    }
}

