package cl.sprint_rocket_ai.ms_context_builder.doc_ddl.infrastructure.in.dtos;

import cl.sprint_rocket_ai.ms_context_builder.doc_ddl.domain.enums.TipoRelacion;
import cl.sprint_rocket_ai.ms_context_builder.doc_ddl.domain.models.Relacion;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Relación (Foreign Key) entre tablas del script DDL.")
public record RelacionResponse(
        @Schema(description = "Columna origen (FK).", example = "cliente_id")
        String columnaOrigen,
        @Schema(description = "Tabla referenciada.", example = "cliente")
        String tablaReferenciada,
        @Schema(description = "Columna referenciada.", example = "id")
        String columnaReferenciada,
        @Schema(description = "Tipo de relación.", implementation = TipoRelacion.class, example = "MUCHOS_A_UNO")
        TipoRelacion tipoRelacion,
        @Schema(description = "Acción ON DELETE.", example = "CASCADE")
        String onDelete,
        @Schema(description = "Acción ON UPDATE.", example = "NO ACTION")
        String onUpdate
) {
    public static RelacionResponse from(Relacion relacion) {
        return new RelacionResponse(
                relacion.getColumnaOrigen(),
                relacion.getTablaReferenciada(),
                relacion.getColumnaReferenciada(),
                relacion.getTipoRelacion(),
                relacion.getOnDelete(),
                relacion.getOnUpdate()
        );
    }
}

