package com.ohgiraffers.collatime.project.controller;

import com.ohgiraffers.collatime.project.model.dto.ProjectDTO;
import com.ohgiraffers.collatime.project.model.service.ProjectService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/project")
public class ProjectController {

    private final ProjectService projectService;

    public ProjectController(ProjectService projectService){
        this.projectService = projectService;

    }
    @GetMapping("/ProjectMain")
    public void projectMain(){}

//    @GetMapping("/createProject")
//    public void createProject(){}

    @PostMapping("/ProjectMain")
    public ModelAndView insertProject(ModelAndView mv, @ModelAttribute ProjectDTO projectDTO){

        System.out.println(projectDTO);

//        projectService.insertProject(projectDTO);

        mv.setViewName("project/ProjectMain");

        return mv;

    }

//    @GetMapping("/test")
//    public void testPage(){}
//
//    @PostMapping("/test")
//    public ModelAndView test(ModelAndView mv, @ModelAttribute ProjectDTO projectDTO){
//
//        System.out.println(projectDTO);
//
////        projectService.insertProject(projectDTO);
//
//        mv.setViewName("project/ProjectMain");
//
//        return mv;
//
//    }
}
