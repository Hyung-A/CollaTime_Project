package com.ohgiraffers.collatime.user.model.dao;

import com.ohgiraffers.collatime.user.model.dto.SignupDTO;
import com.ohgiraffers.collatime.user.model.dto.UserDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    int registUser(SignupDTO signupDTO);

    List<UserDTO> selectAllUser();

    UserDTO findByUsername(String username);

    int modifyPwdByEmail(String email, String code);

    int modifyUser(UserDTO userDTO);
}
