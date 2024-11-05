package com.ohgiraffers.collatime.project.model.service;

import com.ohgiraffers.collatime.project.model.dao.ProjectMapper;
import com.ohgiraffers.collatime.project.model.dto.ProjectDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProjectService {
    private final ProjectMapper projectMapper;

    public ProjectService(ProjectMapper projectMapper){
        this.projectMapper = projectMapper;
    }

    public List<ProjectDTO> getList() {
        return projectMapper.getList();
    }

    public ProjectDTO getProject(ProjectDTO projectDTO) {
        return projectMapper.getProject(projectDTO);
    }


    @Transactional
    public void insertProject(ProjectDTO projectDTO) {
        projectMapper.insertProject(projectDTO);
    }

    @Transactional
    public void updateProject(ProjectDTO projectDTO){
        projectMapper.updateProject(projectDTO);
    }

}
