package com.softer;

import com.softer.config.DatabaseConnection;

public class MainApplication {
    static void main() {
        // Initial connection check
        if (!DatabaseConnection.testConnection()) {
            System.out.println("ERROR: Could not connect to MySQL database.\nPlease check if MySQL is running and the credentials in application.properties are correct.");
        } else {
            System.out.println("PASS: The database has been connected successfully.");
        }
    }
}
