package com.softer.config;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConnection {

    private static String databaseUrl;
    private static String databaseUsername;
    private static String databasePassword;

    static {
        loadProperties();
    }

    private static void loadProperties() {

        Properties properties = new Properties();

        try (InputStream inputStream =
                     DatabaseConnection.class
                             .getClassLoader()
                             .getResourceAsStream("application.properties")) {

            if (inputStream == null) {
                throw new IllegalStateException(
                        "application.properties not found"
                );
            }

            properties.load(inputStream);

            databaseUrl = getConfigValue(
                    "MYSQLDB_URL", properties, "db.url"
            );

            databaseUsername = getConfigValue(
                    "MYSQLDB_USERNAME", properties, "db.username"
            );

            databasePassword = getConfigValue(
                    "MYSQLDB_PASSWORD", properties, "db.password"
            );

        } catch (Exception exception) {
            throw new RuntimeException(
                    "Error loading database configuration",
                    exception
            );
        }
    }

    private static String getConfigValue(
            String environmentVariable,
            Properties properties,
            String propertyName) {

        String value = System.getenv(environmentVariable);

        if (value == null || value.isBlank()) {
            value = properties.getProperty(propertyName);
        }

        if (value == null || value.isBlank()
                || value.startsWith("${")) {
            throw new IllegalStateException(
                    "Missing configuration: " + environmentVariable
            );
        }

        return value;
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                databaseUrl,
                databaseUsername,
                databasePassword
        );
    }

    public static boolean testConnection() {
        try (Connection connection = getConnection()) {
            return connection != null && !connection.isClosed();
        } catch (SQLException exception) {
            exception.printStackTrace();
            return false;
        }
    }
}