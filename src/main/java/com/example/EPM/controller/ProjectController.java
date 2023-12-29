package com.example.EPM.controller;

import com.example.EPM.bean.ProjectBean;
import com.example.EPM.model.Project;
import com.example.EPM.serviceimpl.ProjectServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ProjectController {
    @Autowired
    ProjectServiceImpl projectService;
    @GetMapping("/addProjectPage")
    public String addProductPage(){
        return "AddProject";
    }
    @GetMapping("/viewProject")
    public String viewProduct(Model model){
        List<Project> projects = projectService.getAllProjects();
        model.addAttribute("projects",projects);
        return "ViewProject";
    }
    @PostMapping("/submitProject")
    public String createProject(@ModelAttribute ProjectBean projectBean){
        Project project = new Project();
        project.setName(projectBean.getName());
        projectService.createProject(project);
        return "redirect:/addProjectPage";
    }
    @GetMapping("/deleteProject/{id}")
    public String deleteProject(@PathVariable("id") String id){
        projectService.deleteProject(id);
        return "redirect:/viewProject";
    }
}
