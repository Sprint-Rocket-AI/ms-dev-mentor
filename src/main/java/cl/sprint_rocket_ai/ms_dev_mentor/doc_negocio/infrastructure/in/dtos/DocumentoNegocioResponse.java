package cl.sprint_rocket_ai.ms_dev_mentor.doc_negocio.infrastructure.in.dtos;

import cl.sprint_rocket_ai.ms_dev_mentor.commons.domain.enums.EstadoDocumento;
import cl.sprint_rocket_ai.ms_dev_mentor.doc_negocio.domain.enums.FuenteDocumento;
import cl.sprint_rocket_ai.ms_dev_mentor.commons.domain.enums.TipoDocumento;
import cl.sprint_rocket_ai.ms_dev_mentor.doc_negocio.domain.models.DocumentoNegocio;
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
        @Schema(description = "ID del proyecto al que pertenece.")
        String proyectoId,
        @Schema(description = "Tipo de documento.", implementation = TipoDocumento.class)
        TipoDocumento tipo,
        @Schema(description = "Estado del documento.", implementation = EstadoDocumento.class)
        EstadoDocumento estado,
        @Schema(description = "Origen del documento.", implementation = FuenteDocumento.class)
        FuenteDocumento fuente,
        @Schema(description = "URL de la fuente original.")
        String urlFuente,
        @Schema(description = "Etiquetas asociadas.")
        List<String> tags,
        @Schema(description = "Criterios de aceptación.")
        List<String> criteriosAceptacion,
        @Schema(description = "Resumen ejecutivo del documento.")
        String resumen,
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
                documento.getProyectoId(),
                documento.getTipoDocumento(),
                documento.getEstado(),
                documento.getFuente(),
                documento.getUrlFuente(),
                documento.getTags(),
                documento.getCriteriosAceptacion(),
                documento.getResumen(),
                documento.getFechaCreacion(),
                documento.getFechaActualizacion()
        );
    }
}
