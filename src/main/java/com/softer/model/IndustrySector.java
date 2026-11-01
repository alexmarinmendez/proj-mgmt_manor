package com.softer.model;

public enum IndustrySector {

    FINANCIAL("Financiero"),
    HEALTHCARE("Salud"),
    MANUFACTURING("Manufactura"),
    RETAIL("Comercio minorista"),
    TELECOMMUNICATIONS("Telecomunicaciones");

    private final String description;

    IndustrySector(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return description;
    }

    public static IndustrySector fromDescripcion(String descripcion) {
        for (IndustrySector sector : values()) {
            if (sector.description.equals(descripcion)) {
                return sector;
            }
        }

        throw new IllegalArgumentException(
                "Sector desconocido: " + descripcion
        );
    }
}
