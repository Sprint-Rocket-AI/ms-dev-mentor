package cl.sprint_rocket_ai.ms_context_builder.doc_ddl.domain.models;

import java.util.List;

public class Tabla {

    private String nombre;

    private String esquema;

    private String descripcion;

    private List<Columna> columnas;

    private List<Relacion> relaciones;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEsquema() {
        return esquema;
    }

    public void setEsquema(String esquema) {
        this.esquema = esquema;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<Columna> getColumnas() {
        return columnas;
    }

    public void setColumnas(List<Columna> columnas) {
        this.columnas = columnas;
    }

    public List<Relacion> getRelaciones() {
        return relaciones;
    }

    public void setRelaciones(List<Relacion> relaciones) {
        this.relaciones = relaciones;
    }
}

