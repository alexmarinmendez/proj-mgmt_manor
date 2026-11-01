package com.softer.model;

public enum ProjectStatus {

    PROPOSED("Propuesto"),
    IN_PROGRESS("En progreso"),
    COMPLETED("Completado"),
    CANCELLED("Cancelado");

    private final String description;

    ProjectStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return description;
    }

    public static ProjectStatus fromDescripcion(String descripcion) {
        for (ProjectStatus status : values()) {
            if (status.description.equals(descripcion)) {
                return status;
            }
        }

        throw new IllegalArgumentException(
                "Estado desconocido: " + descripcion
        );
    }
}
