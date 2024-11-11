package com.ohgiraffers.collatime.user.model.service;

import com.ohgiraffers.collatime.user.model.dao.UserMapper;
import com.ohgiraffers.collatime.user.model.dto.SignupDTO;
import com.ohgiraffers.collatime.user.model.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

import static com.ohgiraffers.collatime.common.UserRole.USER;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public int registUser(SignupDTO signupDTO){
        System.out.println(signupDTO);

        signupDTO.setUserPwd((passwordEncoder.encode(signupDTO.getUserPwd())));

        int result = 0;

        try{
            result = userMapper.registUser(signupDTO);
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    public List<UserDTO> selectAllUser(){


        return userMapper.selectAllUser();
    }

    public UserDTO findByUsername(String username) {

        System.out.println(username);
        UserDTO login = userMapper.findByUsername(username);
        System.out.println(login);
        if(!Objects.isNull(login)){
            System.out.println(login);
            return login;
        }else {
            System.out.println("findByUsername fail");
            return null;
        }

    }

    public int modifyPwdByEmail(String mail, String code) {

        String pwd = passwordEncoder.encode(code);

        System.out.println(mail);
        System.out.println(code);
        int result = userMapper.modifyPwdByEmail(mail, pwd);

        return result;
    }

    public int modifyUser(UserDTO userDTO, boolean isModifyPwd) {
        if(isModifyPwd){
            System.out.println("isModify true");
            String newPwd = passwordEncoder.encode(userDTO.getUserPwd());
            userDTO.setUserPwd(newPwd);
        }

        int result = userMapper.modifyUser(userDTO);

        return result;
    }

    public int deleteUser(int userNo) {

        return userMapper.deleteUser(userNo);
    }

    public void modifyProfile(int userNo, String userPicture, String userColor) {

        userMapper.modifyProfile(userNo, userPicture, userColor);

    }
}
