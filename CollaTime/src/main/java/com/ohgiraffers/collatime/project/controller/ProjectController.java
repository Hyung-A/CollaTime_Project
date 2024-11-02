package com.ohgiraffers.collatime.project.controller;

import com.ohgiraffers.collatime.project.model.dto.ProjectDTO;
import com.ohgiraffers.collatime.project.model.service.ProjectService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/project")
public class ProjectController {

    private final ProjectService projectService;

    public ProjectController(ProjectService projectService){
        this.projectService = projectService;

    }
    @GetMapping("/ProjectMain")
    public ModelAndView projectMain(ModelAndView mv){

        projectService.getList().forEach(System.out::println);

        mv.addObject("projectList", projectService.getList());

        mv.setViewName("/project/ProjectMain");

        return mv;

    }

    @PostMapping("/ProjectMain")
    public ModelAndView insertProject(ModelAndView mv, @ModelAttribute ProjectDTO projectDTO){

        System.out.println(projectDTO);

//        projectService.insertProject(projectDTO);

        mv.setViewName("/project/ProjectMain");

        return mv;

    }


}
