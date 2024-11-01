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

        UserDTO userDTO = new UserDTO();
//        userDTO.setUserNo(0);
//        userDTO.setUserId(signupDTO.getUserId());
        signupDTO.setUserPwd((passwordEncoder.encode(signupDTO.getUserPwd())));
//        userDTO.setUserEmail(signupDTO.getUserEmail());
//        userDTO.setUserName(signupDTO.getUserName());
//        userDTO.setUserNickname(signupDTO.getUserNickname());
//        userDTO.setUserPicture("a1");
//        userDTO.setUserColor("red");
//        userDTO.setUserRole(USER);


        System.out.println(userDTO);
        System.out.println(userDTO.getUserNo());
        System.out.println(userDTO.getUserId());
        System.out.println(userDTO.getUserPwd());
        System.out.println(userDTO.getUserEmail());
        System.out.println(userDTO.getUserName());
        System.out.println(userDTO.getUserNickname());
        System.out.println(userDTO.getUserPicture());
        System.out.println(userDTO.getUserColor());
        System.out.println(userDTO.getUserRole());

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
}
