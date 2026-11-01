package com.softer.repository;

import com.softer.config.DatabaseConnection;
import com.softer.model.IndustrySector;
import com.softer.model.Project;
import com.softer.model.ProjectStatus;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MySqlProjectRepository implements ProjectRepository {

    @Override
    public Project save(Project project) {
        String query = "INSERT INTO projects (code, name, client_name, industry_sector, budget, status) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setString(1, project.getCode());
            preparedStatement.setString(2, project.getName());
            preparedStatement.setString(3, project.getClientName());
            preparedStatement.setString(4, project.getIndustrySector().getDescription());
            preparedStatement.setDouble(5, project.getBudget());
            preparedStatement.setString(6, project.getStatus().getDescription());

            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        project.setId(generatedKeys.getLong(1));
                    }
                }
            }
            return project;
        } catch (SQLException exception) {
            throw new RuntimeException("Error saving project to database: " + exception.getMessage(), exception);
        }
    }

    @Override
    public Optional<Project> findById(Long id) {
        String query = "SELECT id, code, name, client_name, industry_sector, budget, status FROM projects WHERE id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setLong(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return Optional.of(mapResultSetToProject(resultSet));
                }
            }
        } catch (SQLException exception) {
            throw new RuntimeException("Error finding project by id: " + exception.getMessage(), exception);
        }
        return Optional.empty();
    }

    @Override
    public Optional<Project> findByCode(String code) {
        String query = "SELECT id, code, name, client_name, industry_sector, budget, status FROM projects WHERE code = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, code);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return Optional.of(mapResultSetToProject(resultSet));
                }
            }
        } catch (SQLException exception) {
            throw new RuntimeException("Error finding project by code: " + exception.getMessage(), exception);
        }
        return Optional.empty();
    }

    @Override
    public List<Project> findAll() {
        List<Project> projects = new ArrayList<>();
        String query = "SELECT id, code, name, client_name, industry_sector, budget, status FROM projects ORDER BY id DESC";
        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                projects.add(mapResultSetToProject(resultSet));
            }
        } catch (SQLException exception) {
            throw new RuntimeException("Error finding all projects: " + exception.getMessage(), exception);
        }
        return projects;
    }

    @Override
    public List<Project> findByIndustrySector(IndustrySector industrySector) {
        List<Project> projects = new ArrayList<>();
        String query = "SELECT id, code, name, client_name, industry_sector, budget, status FROM projects WHERE industry_sector = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, industrySector.getDescription());
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    projects.add(mapResultSetToProject(resultSet));
                }
            }
        } catch (SQLException exception) {
            throw new RuntimeException("Error finding projects by sector: " + exception.getMessage(), exception);
        }
        return projects;
    }

    @Override
    public boolean update(Project project) {
        String query = "UPDATE projects SET name = ?, client_name = ?, industry_sector = ?, budget = ?, status = ? WHERE id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, project.getName());
            preparedStatement.setString(2, project.getClientName());
            preparedStatement.setString(3, project.getIndustrySector().getDescription());
            preparedStatement.setDouble(4, project.getBudget());
            preparedStatement.setString(5, project.getStatus().getDescription());
            preparedStatement.setLong(6, project.getId());

            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException exception) {
            throw new RuntimeException("Error updating project: " + exception.getMessage(), exception);
        }
    }

    @Override
    public boolean deleteById(Long id) {
        String query = "DELETE FROM projects WHERE id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setLong(1, id);
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException exception) {
            throw new RuntimeException("Error deleting project: " + exception.getMessage(), exception);
        }
    }

    private Project mapResultSetToProject(ResultSet resultSet) throws SQLException {
        return new Project(
                resultSet.getLong("id"),
                resultSet.getString("code"),
                resultSet.getString("name"),
                resultSet.getString("client_name"),
                IndustrySector.fromDescripcion(
                        resultSet.getString("industry_sector")
                ),
                resultSet.getDouble("budget"),
                ProjectStatus.fromDescripcion(
                        resultSet.getString("status")
                )
        );
    }
}