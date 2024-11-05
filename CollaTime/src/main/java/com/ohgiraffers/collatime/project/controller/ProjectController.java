package com.ohgiraffers.collatime.project.controller;

import com.ohgiraffers.collatime.project.model.dto.ProjectDTO;
import com.ohgiraffers.collatime.project.model.service.ProjectService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import static java.lang.Integer.parseInt;

@Controller
@RequestMapping("/project")
public class ProjectController {

    private final ProjectService projectService;

    public ProjectController(ProjectService projectService){
        this.projectService = projectService;

    }
    @GetMapping("/projectMain")
    public ModelAndView projectMain(ModelAndView mv){

        projectService.getList().forEach(System.out::println);

        mv.addObject("projectList", projectService.getList());

        mv.setViewName("/project/projectMain");

        return mv;

    }

    @PostMapping("/projectMain")

    public ModelAndView insertProject(ModelAndView mv, @ModelAttribute ProjectDTO projectDTO){

        System.out.println(projectDTO);

        projectService.insertProject(projectDTO);

        mv.addObject("projectList", projectService.getList());

        mv.setViewName("/project/projectMain");

        return mv;

    }

    @GetMapping(value="select-project", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public ProjectDTO selectSpecificProject(ModelAndView mv, @ModelAttribute ProjectDTO projectDTO){
        System.out.println("1234 = " + projectDTO);
        projectDTO = projectService.getProject(projectDTO);
        mv.addObject("select", projectService.getProject(projectDTO));
        mv.setViewName("/project/projectMain");
        return projectDTO;

    }

    @PostMapping("/update-project")
    public ModelAndView updateProject(ModelAndView mv, @ModelAttribute ProjectDTO projectDTO){
        System.out.println(projectDTO);
        projectService.updateProject(projectDTO);
        mv.addObject("projectList", projectService.getList());
        mv.setViewName("/project/projectMain");
        return mv;
    }


}
