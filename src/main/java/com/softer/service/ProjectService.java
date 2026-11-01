package com.softer.service;

import com.softer.model.IndustrySector;
import com.softer.model.Project;
import com.softer.model.ProjectStatus;
import com.softer.repository.ProjectRepository;

import java.util.List;
import java.util.Optional;

public class ProjectService {
    private final ProjectRepository projectRepository;

    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public Project createProject(Project project) {
        if (project.getCode() == null || project.getCode().trim().isEmpty()) {
            throw new IllegalArgumentException("Project code cannot be null or empty.");
        }
        if (project.getName() == null || project.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Project name cannot be null or empty.");
        }
        if (project.getBudget() == null || project.getBudget() <= 0.0) {
            throw new IllegalArgumentException("Project budget must be greater than zero.");
        }

        Optional<Project> existingProject = projectRepository.findByCode(project.getCode());
        if (existingProject.isPresent()) {
            throw new IllegalStateException("A project with code " + project.getCode() + " already exists.");
        }

        if (project.getStatus() == null) {
            project.setStatus(ProjectStatus.PROPOSED);
        }

        return projectRepository.save(project);
    }

    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    public Optional<Project> getProjectById(Long id) {
        return projectRepository.findById(id);
    }

    public boolean updateProject(Project project) {
        if (project.getId() == null) {
            throw new IllegalArgumentException("Project ID cannot be null for update.");
        }
        if (project.getBudget() != null && project.getBudget() <= 0.0) {
            throw new IllegalArgumentException("Project budget must be greater than zero.");
        }
        return projectRepository.update(project);
    }

    public boolean deleteProject(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Project ID cannot be null.");
        }
        return projectRepository.deleteById(id);
    }

    public Double calculateTotalBudgetBySector(IndustrySector industrySector) {
        List<Project> projects = projectRepository.findByIndustrySector(industrySector);
        double totalBudget = 0.0;
        for (Project project : projects) {
            if (project.getBudget() != null) {
                totalBudget += project.getBudget();
            }
        }
        return totalBudget;
    }
}

