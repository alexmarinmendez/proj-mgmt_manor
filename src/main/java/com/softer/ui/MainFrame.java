package com.softer.ui;

import com.softer.model.IndustrySector;
import com.softer.model.Project;
import com.softer.service.ProjectService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class MainFrame extends JFrame {
    private final ProjectService projectService;
    private JTable projectTable;
    private DefaultTableModel tableModel;
    private ProjectFormPanel formPanel;

    public MainFrame(ProjectService projectService) {
        this.projectService = projectService;
        setTitle("MANOR SAC. - Gestión de Proyectos Tecnológicos");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        setupLookAndFeel();
        initComponents();
        refreshProjectTable();
    }

    private void setupLookAndFeel() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception exception) {
            // Fallback to default
        }
    }

    private void initComponents() {
        // Toolbar for sector statistics
        JToolBar toolBar = new JToolBar();
        JButton statsButton = new JButton("Ver Estadísticas por sector");
        statsButton.addActionListener(e -> showSectorStatistics());
        toolBar.add(statsButton);
        add(toolBar, BorderLayout.NORTH);

        // Table
        String[] columnNames = {"ID", "Código", "Proyecto", "Cliente", "Sector", "Presupuesto", "Estado"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        projectTable = new JTable(tableModel);
        projectTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        add(new JScrollPane(projectTable), BorderLayout.CENTER);

        // Control Panel
        JPanel controlPanel = new JPanel(new BorderLayout());
        formPanel = new ProjectFormPanel();
        controlPanel.add(formPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        JButton createButton = new JButton("Registrar nuevo");
        JButton updateButton = new JButton("Actualizar proyecto seleccionado");
        JButton deleteButton = new JButton("Borrar proyecto seleccionado");
        JButton clearButton = new JButton("Limpiar formulario");

        createButton.addActionListener(e -> createProject());
        updateButton.addActionListener(e -> updateSelectedProject());
        deleteButton.addActionListener(e -> deleteSelectedProject());
        clearButton.addActionListener(e -> formPanel.clearForm());

        buttonPanel.add(createButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(clearButton);

        controlPanel.add(buttonPanel, BorderLayout.SOUTH);
        add(controlPanel, BorderLayout.SOUTH);

        // Table selection listener to fill form
        projectTable.getSelectionModel().addListSelectionListener(e -> {
            int selectedRow = projectTable.getSelectedRow();
            if (selectedRow >= 0) {
                Long id = (Long) tableModel.getValueAt(selectedRow, 0);
                projectService.getProjectById(id).ifPresent(formPanel::setProjectInForm);
            }
        });
    }

    private void refreshProjectTable() {
        try {
            tableModel.setRowCount(0);
            List<Project> projects = projectService.getAllProjects();
            for (Project project : projects) {
                tableModel.addRow(new Object[]{
                        project.getId(),
                        project.getCode(),
                        project.getName(),
                        project.getClientName(),
                        project.getIndustrySector(),
                        project.getBudget(),
                        project.getStatus()
                });
            }
        } catch (Exception exception) {
            JOptionPane.showMessageDialog(this, "Error connecting to database: " + exception.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void createProject() {
        try {
            Project project = formPanel.getProjectFromForm();
            projectService.createProject(project);
            refreshProjectTable();
            formPanel.clearForm();
            JOptionPane.showMessageDialog(this, "Nuevo proyecto creado correctamente.");
        } catch (Exception exception) {
            JOptionPane.showMessageDialog(this, "Validation Error: " + exception.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void updateSelectedProject() {
        int selectedRow = projectTable.getSelectedRow();
        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar un proyecto para actualizar.");
            return;
        }

        try {
            Long id = (Long) tableModel.getValueAt(selectedRow, 0);
            Project updatedProject = formPanel.getProjectFromForm();
            updatedProject.setId(id);

            if (projectService.updateProject(updatedProject)) {
                refreshProjectTable();
                JOptionPane.showMessageDialog(this, "El proyecto se actualizó correctamente.");
            }
        } catch (Exception exception) {
            JOptionPane.showMessageDialog(this, "Update Error: " + exception.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deleteSelectedProject() {
        int selectedRow = projectTable.getSelectedRow();
        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar un proyecto para eliminar.");
            return;
        }

        Long id = (Long) tableModel.getValueAt(selectedRow, 0);
        int confirm = JOptionPane.showConfirmDialog(this, "¿Está a punto de eliminar el proyecto con ID " + id + "?", "Confirmar Eliminación", JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            try {
                if (projectService.deleteProject(id)) {
                    refreshProjectTable();
                    formPanel.clearForm();
                    JOptionPane.showMessageDialog(this, "El proyecto se eliminó correctamente.");
                }
            } catch (Exception exception) {
                JOptionPane.showMessageDialog(this, "Delete Error: " + exception.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void showSectorStatistics() {
        IndustrySector sector = (IndustrySector) JOptionPane.showInputDialog(
                this, "Elija un sector:", "Estadísticas por sector",
                JOptionPane.QUESTION_MESSAGE, null, IndustrySector.values(), IndustrySector.values()[0]);

        if (sector != null) {
            Double totalBudget = projectService.calculateTotalBudgetBySector(sector);
            JOptionPane.showMessageDialog(this, "Presupuesto total del sector " + sector + ": €" + String.format("%.2f", totalBudget), "Estadísticas por sector", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
