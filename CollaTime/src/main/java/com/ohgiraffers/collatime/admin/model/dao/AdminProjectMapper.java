package com.ohgiraffers.collatime.admin.model.dao;

import com.ohgiraffers.collatime.admin.model.dto.AdminProjectDTO;
import com.ohgiraffers.collatime.inquiry.model.dto.InquiryDTO;

import java.time.LocalDate;
import java.util.List;

public interface AdminProjectMapper {
    List<AdminProjectDTO> allActiveProject();

    int deleteAllDeactive(List<Integer> list);

    List<AdminProjectDTO> searchProjectByNo(int projectNo);

    List<AdminProjectDTO> searchProjectByName(String projectName);

    List<AdminProjectDTO> searchProjectByEndDate(String projectEndDate);

    List<AdminProjectDTO> searchProjectByProductorNo(int productorNo);

    int authPassUser(InquiryDTO inquiryDTO);
}
