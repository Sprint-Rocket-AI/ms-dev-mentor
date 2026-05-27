package cl.sprint_rocket_ai.ms_dev_mentor.doc_ddl.infrastructure.in.dtos;

import cl.sprint_rocket_ai.ms_dev_mentor.doc_ddl.domain.models.Columna;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Columna de una tabla extraída desde el script DDL.")
public record ColumnaResponse(
        @Schema(description = "Nombre de la columna.", example = "monto")
        String nombre,
        @Schema(description = "Tipo de dato SQL.", example = "NUMERIC(12,2)")
        String tipoDato,
        @Schema(description = "Indica si la columna es Primary Key.", example = "false")
        boolean esPk,
        @Schema(description = "Indica si la columna es Foreign Key.", example = "false")
        boolean esFk,
        @Schema(description = "Indica si la columna admite NULL.", example = "false")
        boolean esNullable,
        @Schema(description = "Indica si la columna es UNIQUE.", example = "false")
        boolean esUnique,
        @Schema(description = "Valor por defecto declarado en el DDL.", example = "0")
        String valorPorDefecto,
        @Schema(description = "Descripción semántica inferida.", example = "Monto del pago en moneda local.")
        String descripcion
) {
    public static ColumnaResponse from(Columna columna) {
        return new ColumnaResponse(
                columna.getNombre(),
                columna.getTipoDato(),
                columna.isEsPk(),
                columna.isEsFk(),
                columna.isEsNullable(),
                columna.isEsUnique(),
                columna.getValorPorDefecto(),
                columna.getDescripcion()
        );
    }
}

