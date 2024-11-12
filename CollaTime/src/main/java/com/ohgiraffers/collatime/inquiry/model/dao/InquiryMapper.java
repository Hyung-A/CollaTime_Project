package com.ohgiraffers.collatime.inquiry.model.dao;

import com.ohgiraffers.collatime.inquiry.model.dto.InquiryDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface InquiryMapper {
    int registInquiry(InquiryDTO inquiryDTO);

    List<InquiryDTO> myInquiryList(int userNo);

    InquiryDTO inquiryInfoByNo(int inquiryNo);
}
