package cl.sprint_rocket_ai.ms_context_builder.documents.infrastructure.in.doc_ddl.dtos;

import cl.sprint_rocket_ai.ms_context_builder.documents.domain.models.doc_ddl.Columna;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

@Schema(description = "DTO para una columna del modelo DDL.")
public record ColumnaRequest(
        @NotBlank(message = "El nombre de la columna es obligatorio")
        @Schema(description = "Nombre de la columna.", example = "monto")
        String nombre,

        @NotBlank(message = "El tipo de dato es obligatorio")
        @Schema(description = "Tipo de dato SQL.", example = "NUMERIC(12,2)")
        String tipoDato,

        @Schema(description = "Indica si la columna es Primary Key.", example = "false")
        boolean esPk,

        @Schema(description = "Indica si la columna es Foreign Key.", example = "false")
        boolean esFk,

        @Schema(description = "Indica si admite NULL.", example = "false")
        boolean esNullable,

        @Schema(description = "Indica si es UNIQUE.", example = "false")
        boolean esUnique,

        @Schema(description = "Valor por defecto declarado en el DDL.", example = "0")
        String valorPorDefecto,

        @Schema(description = "Descripción semántica de la columna.", example = "Monto del pago en moneda local.")
        String descripcion
) {
    public Columna toDomain() {
        Columna col = new Columna();
        col.setNombre(nombre);
        col.setTipoDato(tipoDato);
        col.setEsPk(esPk);
        col.setEsFk(esFk);
        col.setEsNullable(esNullable);
        col.setEsUnique(esUnique);
        col.setValorPorDefecto(valorPorDefecto);
        col.setDescripcion(descripcion);
        return col;
    }
}

