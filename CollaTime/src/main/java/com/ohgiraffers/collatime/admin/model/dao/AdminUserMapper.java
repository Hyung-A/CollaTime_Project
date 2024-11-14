package com.ohgiraffers.collatime.admin.model.dao;

import com.ohgiraffers.collatime.user.model.dto.UserDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AdminUserMapper {
    List<UserDTO> searchAllUser();

    List<UserDTO> searchUserByNo(int userNo);

    List<UserDTO> searchUserById(String userId);

    List<UserDTO> searchUserByEmail(String userEmail);

    List<UserDTO> searchUserByName(String userName);

    List<UserDTO> searchUserByNickname(String userNickname);

    int deleteUserOk(int userNo);
}
