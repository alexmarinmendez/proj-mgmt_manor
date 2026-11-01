package com.softer;

import com.softer.config.DatabaseConnection;
import com.softer.repository.MySqlProjectRepository;
import com.softer.repository.ProjectRepository;
import com.softer.service.ProjectService;
import com.softer.ui.MainFrame;

import javax.swing.*;

public class MainApplication {
    static void main() {
        // Run in the Event Dispatch Thread (EDT) for Swing thread-safety
        SwingUtilities.invokeLater(() -> {
            try {
                // Initialize infrastructure
                ProjectRepository repository = new MySqlProjectRepository();

                // Initialize service with dependency injection
                ProjectService projectService = new ProjectService(repository);

                // Initialize and show the main window
                MainFrame mainFrame = new MainFrame(projectService);
                mainFrame.setVisible(true);

                // Initial connection check
                if (!DatabaseConnection.testConnection()) {
                    JOptionPane.showMessageDialog(mainFrame,
                            "Warning: Could not connect to MySQL database.\nPlease check if MySQL is running and the credentials in application.properties are correct.",
                            "Connection Warning",
                            JOptionPane.WARNING_MESSAGE);
                }
            } catch (Exception exception) {
                JOptionPane.showMessageDialog(null,
                        "Fatal Error starting application: " + exception.getMessage(),
                        "Startup Error",
                        JOptionPane.ERROR_MESSAGE);
                exception.printStackTrace();
            }
        });
    }
}
