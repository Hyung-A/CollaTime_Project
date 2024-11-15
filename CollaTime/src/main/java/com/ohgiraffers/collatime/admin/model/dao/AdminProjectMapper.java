package com.ohgiraffers.collatime.admin.model.dao;

import com.ohgiraffers.collatime.admin.model.dto.AdminProjectDTO;

import java.time.LocalDate;
import java.util.List;

public interface AdminProjectMapper {
    List<AdminProjectDTO> allActiveProject();

    int deleteAllDeactive(List<Integer> list);
}
