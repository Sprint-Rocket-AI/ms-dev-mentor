package cl.sprint_rocket_ai.ms_context_builder.documents.infrastructure.in.doc_ddl.dtos;

import cl.sprint_rocket_ai.ms_context_builder.documents.domain.enums.MotorBaseDatos;
import cl.sprint_rocket_ai.ms_context_builder.documents.domain.models.DocumentoDDL;
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
        "puede construirla manualmente o usar el endpoint de interpretación con IA.",
        example = """
                {
                  "titulo": "Modelo de datos - módulo de pagos",
                  "contenido": "CREATE TABLE pago (id BIGSERIAL PRIMARY KEY, cliente_id BIGINT NOT NULL, monto NUMERIC(12,2) NOT NULL);",
                  "motorBd": "POSTGRESQL",
                  "version": "1.0",
                  "tablas": [
                    {
                      "nombre": "pago",
                      "esquema": "public",
                      "descripcion": "Registra los pagos realizados por los clientes.",
                      "columnas": [
                        {
                          "nombre": "id",
                          "tipoDato": "BIGSERIAL",
                          "esPk": true,
                          "esFk": false,
                          "esNullable": false,
                          "esUnique": true,
                          "valorPorDefecto": null,
                          "descripcion": "Identificador único del pago."
                        },
                        {
                          "nombre": "cliente_id",
                          "tipoDato": "BIGINT",
                          "esPk": false,
                          "esFk": true,
                          "esNullable": false,
                          "esUnique": false,
                          "valorPorDefecto": null,
                          "descripcion": "FK al cliente que realizó el pago."
                        },
                        {
                          "nombre": "monto",
                          "tipoDato": "NUMERIC(12,2)",
                          "esPk": false,
                          "esFk": false,
                          "esNullable": false,
                          "esUnique": false,
                          "valorPorDefecto": "0",
                          "descripcion": "Monto del pago en moneda local."
                        }
                      ],
                      "relaciones": [
                        {
                          "columnaOrigen": "cliente_id",
                          "tablaReferenciada": "cliente",
                          "columnaReferenciada": "id",
                          "tipoRelacion": "MUCHOS_A_UNO",
                          "onDelete": "CASCADE",
                          "onUpdate": "NO ACTION"
                        }
                      ]
                    }
                  ]
                }
                """)
public record DocumentoDDLRequest(
        @NotBlank(message = "El título es obligatorio")
        @Size(max = 200, message = "El título no puede superar los 200 caracteres")
        @Schema(description = "Título del documento.", example = "Modelo de datos - módulo de pagos")
        String titulo,

        @NotBlank(message = "El contenido (script DDL crudo) es obligatorio")
        @Schema(description = "Script SQL DDL crudo (CREATE TABLE, ALTER, etc.).",
                example = "CREATE TABLE pago (id BIGSERIAL PRIMARY KEY, monto NUMERIC(12,2) NOT NULL);")
        String contenido,

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
        target.setContenido(contenido);
        target.setMotorBd(motorBd);
        target.setVersion(version);
        target.setTablas(Optional.ofNullable(tablas).orElseGet(List::of)
                .stream().map(TablaRequest::toDomain).toList());
    }
}
