package com.ohgiraffers.collatime.project.controller;

import com.ohgiraffers.collatime.project.model.dto.ProjectDTO;
import com.ohgiraffers.collatime.project.model.service.ProjectService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

@Controller
@RequestMapping("/projectMainPage")
public class ProjectController {
    /* project Controller */

//    private final ProjectService projectService;
//
//    public ProjectController(ProjectService projectService){
//        this.projectService = projectService;
//
//
//    }

//    @GetMapping("/createProjet")
//    public void createProject(){}
//
//    @PostMapping("/createProject")
//    public ProjectDTO insertProject(Model model, @RequestParam Map<String, String> parameters){
//
//        String projectName = parameters.get("projectName");
//        String projectSummary = parameters.get("projectSummary");
//        String projectPurpose = parameters.get("projectPurpose");
//        SimpleDateFormat startDate = new SimpleDateFormat(parameters.get("startDate"));
//        SimpleDateFormat endDate = new SimpleDateFormat(parameters.get("endDate"));
//        String projectCategory = parameters.get("projectCategory");
//
//
//
//
//        return "";
//    }
}
