package cl.sprint_rocket_ai.ms_context_builder.commons.domain.models;

import cl.sprint_rocket_ai.ms_context_builder.commons.domain.enums.TipoDocumento;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;

public abstract class Documento {
    @Id
    private String id;

    private String titulo;

    private String contenido;

    @Field("fecha_creacion")
    private LocalDateTime fechaCreacion;

    @Field("fecha_actualizacion")
    private LocalDateTime fechaActualizacion;

    public abstract TipoDocumento getTipoDocumento();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }


    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public LocalDateTime getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(LocalDateTime fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }
}

