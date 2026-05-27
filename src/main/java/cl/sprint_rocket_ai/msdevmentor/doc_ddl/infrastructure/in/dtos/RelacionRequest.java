package cl.sprint_rocket_ai.msdevmentor.doc_ddl.infrastructure.in.dtos;

import cl.sprint_rocket_ai.msdevmentor.doc_ddl.domain.enums.TipoRelacion;
import cl.sprint_rocket_ai.msdevmentor.doc_ddl.domain.models.Relacion;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Schema(description = "DTO para una relación (FK) entre tablas.")
public record RelacionRequest(
        @NotBlank(message = "La columna origen es obligatoria")
        @Schema(description = "Columna origen (FK).", example = "cliente_id")
        String columnaOrigen,

        @NotBlank(message = "La tabla referenciada es obligatoria")
        @Schema(description = "Tabla referenciada.", example = "cliente")
        String tablaReferenciada,

        @NotBlank(message = "La columna referenciada es obligatoria")
        @Schema(description = "Columna referenciada.", example = "id")
        String columnaReferenciada,

        @NotNull(message = "El tipo de relación es obligatorio")
        @Schema(description = "Tipo de relación.", implementation = TipoRelacion.class, example = "MUCHOS_A_UNO")
        TipoRelacion tipoRelacion,

        @Schema(description = "Acción ON DELETE.", example = "CASCADE")
        String onDelete,

        @Schema(description = "Acción ON UPDATE.", example = "NO ACTION")
        String onUpdate
) {
    public Relacion toDomain() {
        Relacion rel = new Relacion();
        rel.setColumnaOrigen(columnaOrigen);
        rel.setTablaReferenciada(tablaReferenciada);
        rel.setColumnaReferenciada(columnaReferenciada);
        rel.setTipoRelacion(tipoRelacion);
        rel.setOnDelete(onDelete);
        rel.setOnUpdate(onUpdate);
        return rel;
    }
}

