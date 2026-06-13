package cl.sprint_rocket_ai.ms_context_builder.documents.infrastructure.in.doc_ddl.dtos;

import cl.sprint_rocket_ai.ms_context_builder.documents.domain.models.doc_ddl.Tabla;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;
import java.util.Optional;

@Schema(description = "DTO para una tabla del modelo DDL.")
public record TablaRequest(
        @NotBlank(message = "El nombre de la tabla es obligatorio")
        @Schema(description = "Nombre de la tabla.", example = "pago")
        String nombre,

        @Schema(description = "Esquema al que pertenece.", example = "public")
        String esquema,

        @Schema(description = "Descripción semántica de la tabla.", example = "Registra los pagos realizados por los clientes.")
        String descripcion,

        @NotEmpty(message = "La tabla debe tener al menos una columna")
        @Valid
        @Schema(description = "Columnas de la tabla.")
        List<ColumnaRequest> columnas,

        @Valid
        @Schema(description = "Relaciones (FKs) salientes.")
        List<RelacionRequest> relaciones
) {
    public Tabla toDomain() {
        Tabla tabla = new Tabla();
        tabla.setNombre(nombre);
        tabla.setEsquema(esquema);
        tabla.setDescripcion(descripcion);
        tabla.setColumnas(Optional.ofNullable(columnas).orElseGet(List::of)
                .stream().map(ColumnaRequest::toDomain).toList());
        tabla.setRelaciones(Optional.ofNullable(relaciones).orElseGet(List::of)
                .stream().map(RelacionRequest::toDomain).toList());
        return tabla;
    }
}

