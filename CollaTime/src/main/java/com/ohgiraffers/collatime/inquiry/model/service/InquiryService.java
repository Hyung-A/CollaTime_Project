package com.ohgiraffers.collatime.inquiry.model.service;

import com.ohgiraffers.collatime.inquiry.model.dao.InquiryMapper;
import com.ohgiraffers.collatime.inquiry.model.dto.InquiryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InquiryService {
    private final InquiryMapper inquiryMapper;

    @Autowired
    public InquiryService(InquiryMapper inquiryMapper){
        this.inquiryMapper = inquiryMapper;
    }

    public int registInquiry(InquiryDTO inquiryDTO) {
        inquiryDTO.setInquiryNo(0);
        inquiryDTO.setInquiryStatus("문의요청");
        System.out.println(inquiryDTO);

        return inquiryMapper.registInquiry(inquiryDTO);
    }

    public List<InquiryDTO> myInquiryList(int userNo) {

        List<InquiryDTO> inquiryDTOList = inquiryMapper.myInquiryList(userNo);

        return inquiryDTOList;
    }

    public InquiryDTO inquiryInfoByNo(int inquiryNo) {
        return inquiryMapper.inquiryInfoByNo(inquiryNo);
    }

    public int registPassAuthInquiry(InquiryDTO inquiryDTO) {
        return inquiryMapper.registPassAuthInquiry(inquiryDTO);
    }
}
