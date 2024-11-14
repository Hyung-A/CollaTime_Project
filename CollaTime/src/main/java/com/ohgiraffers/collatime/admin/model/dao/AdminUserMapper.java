package com.ohgiraffers.collatime.admin.model.dao;

import com.ohgiraffers.collatime.user.model.dto.UserDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AdminUserMapper {
    List<UserDTO> searchAllUser();

    List<UserDTO> searchUserByNo(int userNo);
}
