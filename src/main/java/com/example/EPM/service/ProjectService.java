package com.example.EPM.service;

import com.example.EPM.model.Project;
import jakarta.persistence.criteria.CriteriaBuilder;

import java.util.List;
import java.util.Set;

public interface ProjectService {
    void createProject(Project project);
    void deleteProject(String id);
    Project getProjectById(String id);
    List<Project> getAllProjects();
    Set<Project> getAllProjectsInSet();
}
