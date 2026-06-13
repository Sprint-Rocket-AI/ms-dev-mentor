package cl.sprint_rocket_ai.ms_dev_mentor.doc_ddl.infrastructure.in.dtos;

import cl.sprint_rocket_ai.ms_dev_mentor.doc_ddl.domain.enums.MotorBaseDatos;
import cl.sprint_rocket_ai.ms_dev_mentor.commons.domain.enums.TipoDocumento;
import cl.sprint_rocket_ai.ms_dev_mentor.doc_ddl.domain.models.DocumentoDDL;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Schema(description = "DTO de respuesta de un Documento DDL con su estructura interpretada.")
public record DocumentoDDLResponse(
        @Schema(description = "ID único del documento.", example = "6653d50711312d1174a61515")
        String id,
        @Schema(description = "Título del documento.", example = "Modelo de datos - módulo de pagos")
        String titulo,
        @Schema(description = "Script DDL crudo enviado por el usuario.")
        String contenido,
        @Schema(description = "Tipo de documento.", implementation = TipoDocumento.class)
        TipoDocumento tipo,
        @Schema(description = "Motor de base de datos.", implementation = MotorBaseDatos.class)
        MotorBaseDatos motorBd,
        @Schema(description = "Versión del modelo de datos inferida.", example = "1.0")
        String version,
        @Schema(description = "Tablas detectadas en el script.")
        List<TablaResponse> tablas,
        @Schema(description = "Fecha de creación.")
        LocalDateTime fechaCreacion,
        @Schema(description = "Fecha de última actualización.")
        LocalDateTime fechaActualizacion
) {
    public static DocumentoDDLResponse from(DocumentoDDL documento) {
        return new DocumentoDDLResponse(
                documento.getId(),
                documento.getTitulo(),
                documento.getContenido(),
                documento.getTipoDocumento(),
                documento.getMotorBd(),
                documento.getVersion(),
                Optional.ofNullable(documento.getTablas()).orElseGet(List::of)
                        .stream().map(TablaResponse::from).toList(),
                documento.getFechaCreacion(),
                documento.getFechaActualizacion()
        );
    }
}

