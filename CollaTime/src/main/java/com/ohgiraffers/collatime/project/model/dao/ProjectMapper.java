package com.ohgiraffers.collatime.project.model.dao;

import com.ohgiraffers.collatime.project.model.dto.ProjectDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProjectMapper {
    /* ProjectMapper */


    void insertProject(ProjectDTO projectDTO);



}
