package com.ohgiraffers.collatime.project.controller;

import com.ohgiraffers.collatime.common.MainController;
import com.ohgiraffers.collatime.mail.MailDTO;
import com.ohgiraffers.collatime.mail.MailService;
import com.ohgiraffers.collatime.project.model.dto.InviteMemberDTO;
import com.ohgiraffers.collatime.project.model.dto.ProjectDTO;
import com.ohgiraffers.collatime.project.model.service.ProjectService;
import jakarta.mail.MessagingException;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import static java.lang.Integer.parseInt;

@Controller
@RequestMapping("/project")
public class ProjectController {

    private final ProjectService projectService;
    private final MailService mailService;
//    private final Logger Logger = (java.util.logging.Logger) LoggerFactory.getLogger(MainController.class.getName());

    public ProjectController(ProjectService projectService, MailService mailService){
        this.projectService = projectService;
        this.mailService = mailService;

    }
    @GetMapping("/projectmain")
    public ModelAndView projectmain(ModelAndView mv){
        projectService.getList().forEach(System.out::println);
        mv.addObject("projectList", projectService.getList());
        mv.setViewName("/project/projectmain");
        return mv;
    }

    @PostMapping(value = "/projectmain")
    public ModelAndView insertProject(ModelAndView mv, @ModelAttribute ProjectDTO projectDTO){
        System.out.println(projectDTO);
        projectService.insertProject(projectDTO);
        mv.addObject("projectList", projectService.getList());
        mv.setViewName("redirect:/project/projectmain");
        return mv;
    }

    @GetMapping(value="selectproject", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public ProjectDTO selectSpecificProject(ModelAndView mv, @ModelAttribute ProjectDTO projectDTO){
        projectDTO = projectService.getProject(projectDTO);
        mv.addObject("select", projectService.getProject(projectDTO));
        System.out.println("select/update = " + projectDTO);
        mv.setViewName("/project/projectmain");
        return projectDTO;

    }

    @PostMapping("/updateproject")
    public ModelAndView updateProject(ModelAndView mv, @ModelAttribute ProjectDTO projectDTO){
        projectService.updateProject(projectDTO);
        mv.addObject("projectList", projectService.getList());
        System.out.println("update" + projectDTO);
        mv.setViewName("redirect:/project/projectmain");
        return mv;
    }

    @PostMapping("/deleteproject")
    public ModelAndView deleteProject(ModelAndView mv, @ModelAttribute ProjectDTO projectDTO){
        projectService.deleteProject(projectDTO);
        mv.addObject("projectList", projectService.getList());
        System.out.println("delete" + projectDTO);
        mv.setViewName("redirect:/project/projectmain");
        return mv;
    }

//    @ResponseBody
//    @RequestMapping(value="/emaillist", method = {RequestMethod.POST})
//    public String emailList(@RequestParam(value="emailList[]")ArrayList<String>emailArray) {
//        Logger.info(emailArray);
//
//    }
//

}
