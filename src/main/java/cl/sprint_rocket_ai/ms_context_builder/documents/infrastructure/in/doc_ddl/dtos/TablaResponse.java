package cl.sprint_rocket_ai.ms_context_builder.documents.infrastructure.in.doc_ddl.dtos;

import cl.sprint_rocket_ai.ms_context_builder.documents.domain.models.doc_ddl.Tabla;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;
import java.util.Optional;

@Schema(description = "Tabla extraída desde el script DDL.")
public record TablaResponse(
        @Schema(description = "Nombre de la tabla.", example = "pago")
        String nombre,
        @Schema(description = "Esquema al que pertenece.", example = "public")
        String esquema,
        @Schema(description = "Descripción semántica inferida.", example = "Registra los pagos realizados por los clientes.")
        String descripcion,
        @Schema(description = "Columnas de la tabla.")
        List<ColumnaResponse> columnas,
        @Schema(description = "Relaciones (FKs) salientes.")
        List<RelacionResponse> relaciones
) {
    public static TablaResponse from(Tabla tabla) {
        return new TablaResponse(
                tabla.getNombre(),
                tabla.getEsquema(),
                tabla.getDescripcion(),
                Optional.ofNullable(tabla.getColumnas()).orElseGet(List::of)
                        .stream().map(ColumnaResponse::from).toList(),
                Optional.ofNullable(tabla.getRelaciones()).orElseGet(List::of)
                        .stream().map(RelacionResponse::from).toList()
        );
    }
}

