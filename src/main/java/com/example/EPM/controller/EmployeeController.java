package com.example.EPM.controller;

import com.example.EPM.bean.EmployeeBeanClass;
import com.example.EPM.model.Employee;
import com.example.EPM.model.Project;
import com.example.EPM.serviceimpl.EmployeeServiceImpl;
import com.example.EPM.serviceimpl.ProjectServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.*;

@Controller
public class EmployeeController {
    @Autowired
    ProjectServiceImpl projectService;
    @Autowired
    EmployeeServiceImpl employeeService;
    @GetMapping("/homes")
    public String home(){
        return "home";
    }

    @GetMapping("/addEmpPage")
    public String addEmplPage(Model model){
        List<Project> projects = projectService.getAllProjects();
        model.addAttribute("projects",projects);
        return "AddEmployee";
    }

    @GetMapping("/viewEmpl")
    public String viewEmpl(Model model){
        List<Employee> employees = employeeService.getAllEmployees();

        model.addAttribute("employees",employees);
        return "ViewEmployee";
    }
    @GetMapping("/deleteEmployee/{id}")
    public String deleteEmployee(@PathVariable("id") Integer id){
        employeeService.deleteEmployee(id);
        return "redirect:/viewEmpl";
    }
    @PostMapping("/addEmpl")
    public String addEmployee(@ModelAttribute EmployeeBeanClass employeeBean){
        Project project = projectService.getProjectById(employeeBean.getProjectId());

        Employee employee = null;
        Set<Project> projectList = null;
        if(employeeBean.getId() != null){
            employee = employeeService.getEmployeeById(employeeBean.getId());
            projectList = employee.getProjects();
        }
        else {
            employee = new Employee();
            projectList = new HashSet<>();
        }
        projectList.add(project);
        employee.setName(employeeBean.getName());
        employee.setDomain(employeeBean.getDomain());
        employee.setProjects(projectList);
        employeeService.createEmployee(employee);
        return "redirect:/addEmpPage";
    }
    @GetMapping("/updateProject/{id}")
    public String UpdateProject(@PathVariable("id") Integer id, Model model){
        Set<Project> newset = new HashSet<>();
        Employee employee = employeeService.getEmployeeById(id);
        Set<Project> EmpProjects = projectService.getAllProjectsInSet();
        for (Project n:EmpProjects) {
            newset.add(n);
        }
        model.addAttribute("set",newset);
        model.addAttribute("emp",employee);
        return "AddNewProject";
    }
}
