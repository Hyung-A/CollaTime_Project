package com.ohgiraffers.collatime.visit.model.service;

import com.ohgiraffers.collatime.visit.model.dao.VisitMapper;
import com.ohgiraffers.collatime.visit.model.dto.VisitDTO;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class VisitService {

    private final VisitMapper visitMapper;

    public VisitService(VisitMapper visitMapper){
        this.visitMapper = visitMapper;
    }

    public VisitDTO visitDate(LocalDate date){

        return visitMapper.visitDate(date);
    }

    public void loginUser() {
        System.out.println("로그인 했다");

        LocalDate now = LocalDate.now();
        System.out.println(now);

        VisitDTO visitUser = visitDate(now);
        System.out.println("불러온 오늘 방문자"+ visitUser);
        if(visitUser==null){
            VisitDTO newDate = new VisitDTO();
            newDate.setVisitDate(now);
            newDate.setVisitCount(1);
            visitMapper.newDateLoginUser(newDate);
        }else {
            visitUser.setVisitCount(visitUser.getVisitCount()+1);
            System.out.println("todayLoginUser"+visitUser);
            visitMapper.todayLoginUser(visitUser);
        }

    }
}

