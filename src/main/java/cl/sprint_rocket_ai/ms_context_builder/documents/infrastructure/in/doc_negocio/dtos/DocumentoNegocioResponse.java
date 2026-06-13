package cl.sprint_rocket_ai.ms_context_builder.documents.infrastructure.in.doc_negocio.dtos;

import cl.sprint_rocket_ai.ms_context_builder.documents.domain.enums.TipoDocumento;
import cl.sprint_rocket_ai.ms_context_builder.documents.domain.models.DocumentoNegocio;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;
import java.util.List;

@Schema(description = "DTO de respuesta de un Documento de Negocio.")
public record DocumentoNegocioResponse(
        @Schema(description = "ID único del documento.", example = "6653d50711312d1174a61515")
        String id,

        @Schema(description = "Título del documento.")
        String titulo,

        @Schema(description = "Contenido del documento.")
        String contenido,

        @Schema(description = "Tipo de documento.", implementation = TipoDocumento.class)
        TipoDocumento tipo,

        @Schema(description = "Etiquetas asociadas.")
        List<String> tags,

        @Schema(description = "Criterios de aceptación.")
        List<String> criteriosAceptacion,

        @Schema(description = "Fecha de creación.")
        LocalDateTime fechaCreacion,

        @Schema(description = "Fecha de la última actualización.")
        LocalDateTime fechaActualizacion
) {
    public static DocumentoNegocioResponse from(DocumentoNegocio documento) {
        return new DocumentoNegocioResponse(
                documento.getId(),
                documento.getTitulo(),
                documento.getContenido(),
                documento.getTipoDocumento(),
                documento.getTags(),
                documento.getCriteriosAceptacion(),
                documento.getFechaCreacion(),
                documento.getFechaActualizacion()
        );
    }
}
