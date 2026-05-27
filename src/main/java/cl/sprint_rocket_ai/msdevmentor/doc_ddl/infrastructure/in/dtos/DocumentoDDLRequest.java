package cl.sprint_rocket_ai.msdevmentor.doc_ddl.infrastructure.in.dtos;

import cl.sprint_rocket_ai.msdevmentor.commons.domain.enums.EstadoDocumento;
import cl.sprint_rocket_ai.msdevmentor.doc_ddl.domain.enums.MotorBaseDatos;
import cl.sprint_rocket_ai.msdevmentor.doc_ddl.domain.models.DocumentoDDL;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;
import java.util.Optional;

@Schema(description = "DTO para la creación/actualización de un Documento DDL. " +
        "Recibe la estructura ya modelada (tablas, columnas, relaciones); el cliente " +
        "puede construirla manualmente o usar el endpoint de interpretación con IA.")
public record DocumentoDDLRequest(
        @NotBlank(message = "El título es obligatorio")
        @Size(max = 200, message = "El título no puede superar los 200 caracteres")
        @Schema(description = "Título del documento.", example = "Modelo de datos - módulo de pagos")
        String titulo,

        @NotBlank(message = "El proyectoId es obligatorio")
        @Schema(description = "ID del proyecto al que pertenece el documento.", example = "6653d50711312d1174a61516")
        String proyectoId,

        @NotBlank(message = "El contenido (script DDL crudo) es obligatorio")
        @Schema(description = "Script SQL DDL crudo (CREATE TABLE, ALTER, etc.).",
                example = "CREATE TABLE pago (id BIGSERIAL PRIMARY KEY, monto NUMERIC(12,2) NOT NULL);")
        String contenido,

        @NotNull(message = "El estado es obligatorio")
        @Schema(description = "Estado actual del documento.", implementation = EstadoDocumento.class, example = "BORRADOR")
        EstadoDocumento estado,

        @NotNull(message = "El motor de base de datos es obligatorio")
        @Schema(description = "Motor de base de datos.", implementation = MotorBaseDatos.class, example = "POSTGRESQL")
        MotorBaseDatos motorBd,

        @Schema(description = "Versión del modelo de datos.", example = "1.0")
        String version,

        @NotEmpty(message = "Debe declarar al menos una tabla")
        @Valid
        @Schema(description = "Tablas del modelo.")
        List<TablaRequest> tablas
) {
    /**
     * Aplica este Request sobre un modelo de dominio existente (mutación).
     * Save invoca con {@code new DocumentoDDL()}; Update con el documento
     * recuperado de la BD (preservando id y fechaCreacion).
     */
    public void applyTo(DocumentoDDL target) {
        target.setTitulo(titulo);
        target.setProyectoId(proyectoId);
        target.setContenido(contenido);
        target.setEstado(estado);
        target.setMotorBd(motorBd);
        target.setVersion(version);
        target.setTablas(Optional.ofNullable(tablas).orElseGet(List::of)
                .stream().map(TablaRequest::toDomain).toList());
    }
}

