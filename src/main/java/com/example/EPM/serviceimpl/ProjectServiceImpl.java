package com.example.EPM.serviceimpl;

import com.example.EPM.dao.ProjectRepository;
import com.example.EPM.model.Employee;
import com.example.EPM.model.Project;
import com.example.EPM.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
public class ProjectServiceImpl implements ProjectService {
    static long count = 1;
    @Autowired
    ProjectRepository projectRepository;
    @Override
    public void createProject(Project project) {
        String projectId = UUID.randomUUID().toString();
        project.setUid(count++);
        project.setId(projectId);
        projectRepository.save(project);
    }

    @Override
    public void deleteProject(String id) {
        Project project = projectRepository.findById(id).orElse(null);
        if (project != null) {
            // Remove the project association from employees
            for (Employee employee : project.getEmployees()) {
                employee.getProjects().remove(project);
            }
            project.getEmployees().clear();
            projectRepository.delete(project);
        }
    }

    @Override
    public Project getProjectById(String id) {
        return projectRepository.findById(id).orElse(null);
    }

    @Override
    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    @Override
    public Set<Project> getAllProjectsInSet() {
        Set<Project> set = new HashSet<>();
        for(Project n : projectRepository.findAll()){
            set.add(n);
        }
        return set;
    }


}
