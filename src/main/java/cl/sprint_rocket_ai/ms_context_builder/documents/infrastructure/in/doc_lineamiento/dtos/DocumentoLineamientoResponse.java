package cl.sprint_rocket_ai.ms_context_builder.documents.infrastructure.in.doc_lineamiento.dtos;

import cl.sprint_rocket_ai.ms_context_builder.documents.domain.enums.TipoDocumento;
import cl.sprint_rocket_ai.ms_context_builder.documents.domain.models.DocumentoLineamiento;
import cl.sprint_rocket_ai.ms_context_builder.documents.domain.models.DocumentoResponse;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;
import java.util.List;

@Schema(description = "DTO de respuesta de un Documento Lineamiento.")
public record DocumentoLineamientoResponse(
        @Schema(description = "ID único del documento.", example = "6653d50711312d1174a61517")
        String id,

        @Schema(description = "Título del lineamiento.", example = "Lineamiento de diseño de microservicios")
        String titulo,

        @Schema(description = "Contenido del lineamiento.", example = "Cada microservicio debe ser autónomo...")
        String contenido,

        @Schema(description = "Tipo de documento.", implementation = TipoDocumento.class)
        TipoDocumento tipo,

        @Schema(description = "Etiquetas de búsqueda.", example = "[\"spring-boot\", \"observabilidad\"]")
        List<String> tags,

        @Schema(description = "Fecha de creación del documento.")
        LocalDateTime fechaCreacion,

        @Schema(description = "Fecha de la última actualización.")
        LocalDateTime fechaActualizacion
) implements DocumentoResponse {
    public static DocumentoLineamientoResponse from(DocumentoLineamiento documento) {
        return new DocumentoLineamientoResponse(
                documento.getId(),
                documento.getTitulo(),
                documento.getContenido(),
                documento.getTipoDocumento(),
                documento.getTags(),
                documento.getFechaCreacion(),
                documento.getFechaActualizacion()
        );
    }
}

