package com.softer.repository;

import com.softer.model.IndustrySector;
import com.softer.model.Project;

import java.util.List;
import java.util.Optional;

public interface ProjectRepository {
    Project save(Project project);
    Optional<Project> findById(Long id);
    Optional<Project> findByCode(String code);
    List<Project> findAll();
    List<Project> findByIndustrySector(IndustrySector industrySector);
    boolean update(Project project);
    boolean deleteById(Long id);
}

