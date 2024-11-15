package com.ohgiraffers.collatime.admin.model.dao;

import com.ohgiraffers.collatime.inquiry.model.dto.InquiryDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AdminInquiryMapper {
    List<InquiryDTO> passAuth();

    List<InquiryDTO> require();

    List<InquiryDTO> answer();

    List<InquiryDTO> read();

    InquiryDTO searchInquiry(int inquiryNo);

    int inquiryUpdateStatus(InquiryDTO inquiryDTO);
}
