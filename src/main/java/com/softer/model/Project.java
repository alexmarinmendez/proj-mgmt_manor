package com.softer.model;

import java.util.Objects;

public class Project {
    private Long id;
    private String code;
    private String name;
    private String clientName;
    private IndustrySector industrySector;
    private Double budget;
    private ProjectStatus status;

    public Project() {
    }

    public Project(Long id, String code, String name, String clientName, IndustrySector industrySector, Double budget, ProjectStatus status) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.clientName = clientName;
        this.industrySector = industrySector;
        this.budget = budget;
        this.status = status;
    }

    public Project(String code, String name, String clientName, IndustrySector industrySector, Double budget, ProjectStatus status) {
        this(null, code, name, clientName, industrySector, budget, status);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public IndustrySector getIndustrySector() {
        return industrySector;
    }

    public void setIndustrySector(IndustrySector industrySector) {
        this.industrySector = industrySector;
    }

    public Double getBudget() {
        return budget;
    }

    public void setBudget(Double budget) {
        this.budget = budget;
    }

    public ProjectStatus getStatus() {
        return status;
    }

    public void setStatus(ProjectStatus status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Project project = (Project) object;
        return Objects.equals(id, project.id) && Objects.equals(code, project.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, code);
    }

    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", clientName='" + clientName + '\'' +
                ", industrySector=" + industrySector +
                ", budget=" + budget +
                ", status=" + status +
                '}';
    }
}
