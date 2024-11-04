package com.ohgiraffers.collatime.project.controller;

import com.ohgiraffers.collatime.project.model.dto.ProjectDTO;
import com.ohgiraffers.collatime.project.model.service.ProjectService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

//        projectService.getList().forEach(System.out::println);

        mv.addObject("projectList", projectService.getList());

        mv.setViewName("/project/ProjectMain");

        return mv;

    }

    @PostMapping("/ProjectMain")
    public ModelAndView insertProject(ModelAndView mv, @ModelAttribute ProjectDTO projectDTO){

        System.out.println(projectDTO);

        projectService.insertProject(projectDTO);

        mv.addObject("projectList", projectService.getList());

        mv.setViewName("/project/ProjectMain");

        return mv;

    }
    @GetMapping(value="selectProject", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public ProjectDTO selectSpecificProject(ModelAndView mv, @ModelAttribute ProjectDTO projectDTO){
//        System.out.println("1234 = " + projectDTO);
//        System.out.println("234 = " + projectService.getList());
        projectDTO = projectService.getProject();
        mv.addObject("select", projectService.getProject());
        mv.setViewName("/project/ProjectMain");
        System.out.println(projectDTO);
        return projectDTO;

    }

//    @PostMapping("/projectUpdate")
//    public String updateProject(ProjectDTO projectDTO, RedirectAttributes rttr){
//
//        projectService.updateProject(projectDTO);
//
//        rttr.addFlashAttribute("message", "프로젝트 내용 수정에 성공하였습니다.");
//
//        return "redirect:/project/ProjectMain";
//
//    }


}
