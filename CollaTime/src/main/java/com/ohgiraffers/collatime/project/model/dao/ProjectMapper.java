package com.ohgiraffers.collatime.project.model.dao;

import com.ohgiraffers.collatime.project.model.dto.ProjectDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProjectMapper {
    /* ProjectMapper */


    List<ProjectDTO> getList();

    void insertProject(ProjectDTO projectDTO);

}
