package com.softer.ui;

import com.softer.model.IndustrySector;
import com.softer.model.Project;
import com.softer.model.ProjectStatus;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class ProjectFormPanel extends JPanel {
    private JTextField codeField;
    private JTextField nameField;
    private JTextField clientField;
    private JComboBox<IndustrySector> sectorCombo;
    private JComboBox<ProjectStatus> statusCombo;
    private JSpinner budgetSpinner;

    public ProjectFormPanel() {
        setLayout(new GridBagLayout());
        setBorder(new EmptyBorder(10, 10, 10, 10));
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);

        codeField = new JTextField(15);
        nameField = new JTextField(25);
        clientField = new JTextField(25);
        sectorCombo = new JComboBox<>(IndustrySector.values());
        statusCombo = new JComboBox<>(ProjectStatus.values());
        budgetSpinner = new JSpinner(new SpinnerNumberModel(1000.0, 0.0, 10000000.0, 100.0));

        addLabelAndComponent("Código para el Proyecto:", codeField, gridBagConstraints, 0);
        addLabelAndComponent("Nombre del Proyecto:", nameField, gridBagConstraints, 1);
        addLabelAndComponent("Nombre del Cliente:", clientField, gridBagConstraints, 2);
        addLabelAndComponent("Sector:", sectorCombo, gridBagConstraints, 3);
        addLabelAndComponent("Estado del Proyecto:", statusCombo, gridBagConstraints, 4);
        addLabelAndComponent("Presupuesto (€):", budgetSpinner, gridBagConstraints, 5);
    }

    private void addLabelAndComponent(String labelText, JComponent component, GridBagConstraints gridBagConstraints, int row) {
        gridBagConstraints.gridy = row;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.weightx = 0.0;
        add(new JLabel(labelText), gridBagConstraints);

        gridBagConstraints.gridx = 1;
        gridBagConstraints.weightx = 1.0;
        add(component, gridBagConstraints);
    }

    public Project getProjectFromForm() {
        return new Project(
                codeField.getText().trim(),
                nameField.getText().trim(),
                clientField.getText().trim(),
                (IndustrySector) sectorCombo.getSelectedItem(),
                (Double) budgetSpinner.getValue(),
                (ProjectStatus) statusCombo.getSelectedItem()
        );
    }

    public void setProjectInForm(Project project) {
        codeField.setText(project.getCode());
        codeField.setEditable(false);
        nameField.setText(project.getName());
        clientField.setText(project.getClientName());
        sectorCombo.setSelectedItem(project.getIndustrySector());
        statusCombo.setSelectedItem(project.getStatus());
        budgetSpinner.setValue(project.getBudget());
    }

    public void clearForm() {
        codeField.setText("");
        codeField.setEditable(true);
        nameField.setText("");
        clientField.setText("");
        sectorCombo.setSelectedIndex(0);
        statusCombo.setSelectedIndex(0);
        budgetSpinner.setValue(1000.0);
    }
}
