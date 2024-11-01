package com.ohgiraffers.collatime.project.model.service;

import com.ohgiraffers.collatime.project.model.dao.ProjectMapper;
import com.ohgiraffers.collatime.project.model.dto.ProjectDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProjectService {
    private final ProjectMapper projectMapper;

    public ProjectService(ProjectMapper projectMapper){
        this.projectMapper = projectMapper;
    }

//    @Transactional
//    public void insertProject(ProjectDTO projectDTO) {
//        projectMapper.insertProject(projectDTO);
//    }

    /* projectService */



}
