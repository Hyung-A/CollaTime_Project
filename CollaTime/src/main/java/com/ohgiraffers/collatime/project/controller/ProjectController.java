package com.ohgiraffers.collatime.project.controller;

import com.ohgiraffers.collatime.project.model.dto.ProjectDTO;
import com.ohgiraffers.collatime.project.model.service.ProjectService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

@Controller
@RequestMapping("/project")
public class ProjectController {
    /* project Controller */

    private final ProjectService projectService;

    public ProjectController(ProjectService projectService){
        this.projectService = projectService;

    }
    @GetMapping("/ProjectMain")
    public void projectMain(){}

    @GetMapping("/createProject")
    public void createProject(){}

//    @GetMapping("/project/test")
//    public void testPage(){}

//    @PostMapping("/createProject")
//    public ModelAndView insertProject(ModelAndView mv, @RequestParam Map<String,String>parameters){
//
//        String projectName = parameters.get("projectName");
//        String projectSummary = parameters.get("projectSummary");
//        String projectPurpose = parameters.get("projectPurpose");
//        SimpleDateFormat startDate = new SimpleDateFormat(parameters.get("startDate"));
//       SimpleDateFormat endDate = new SimpleDateFormat(parameters.get("endDate"));
//        String projectCategory = parameters.get("category");
//
//        System.out.println(projectName);
//
//        mv.setViewName("project/ProjectMain");
//
//        return mv;
//
////        System.out.println(dto.getProjectName());
//
//
//
//    }

//    @PostMapping("/createProject")
//    public ModelAndView insertProject(ModelAndView mv, ProjectDTO projectDTO){
//
//        projectService.insertProject(projectDTO);
//
//        mv.setViewName("/project/ProjectMain");
//
//        return mv;
//
//    }
}
