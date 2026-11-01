-- TDD Example: MySQL Schema
CREATE DATABASE IF NOT EXISTS projects_db;
USE projects_db;

CREATE TABLE IF NOT EXISTS projects (
                                        id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                        code VARCHAR(30) NOT NULL UNIQUE,
    name VARCHAR(150) NOT NULL,
    client_name VARCHAR(150) NOT NULL,
    industry_sector VARCHAR(50) NOT NULL,
    budget DOUBLE NOT NULL,
    status VARCHAR(30) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
    );

-- Sample data for consulting projects
INSERT INTO projects (code, name, client_name, industry_sector, budget, status) VALUES
                                                                                    ('PRJ-FIN-001', 'Sistema de detección de fraudes mediante IA', 'Global Financial Bank', 'Financiero', 150000.0, 'En progreso'),
                                                                                    ('PRJ-HLT-002', 'Aplicación de telemedicina para la atención sanitaria', 'Sanitas Care', 'Salud', 95000.0, 'Propuesto'),
                                                                                    ('PRJ-MNF-003', 'Analytics IoT para fábricas inteligentes', 'Indumil Manufacturing', 'Manufactura', 210000.0, 'En progreso');
