package cl.sprint_rocket_ai.ms_dev_mentor.doc_lineamiento.infrastructure.in.dtos;

import cl.sprint_rocket_ai.ms_dev_mentor.commons.domain.enums.EstadoDocumento;
import cl.sprint_rocket_ai.ms_dev_mentor.commons.domain.enums.TipoDocumento;
import cl.sprint_rocket_ai.ms_dev_mentor.doc_lineamiento.domain.models.DocumentoLineamiento;
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
        @Schema(description = "ID del proyecto al que pertenece.", example = "6653d50711312d1174a61516")
        String proyectoId,
        @Schema(description = "Tipo de documento.", implementation = TipoDocumento.class)
        TipoDocumento tipo,
        @Schema(description = "Estado del documento.", implementation = EstadoDocumento.class)
        EstadoDocumento estado,
        @Schema(description = "Núcleo del conocimiento usado por el sistema RAG.", example = "Todo microservicio debe exponer un health check.")
        String lineamiento,
        @Schema(description = "Dominio al que aplica el lineamiento.", example = "microservicios")
        String dominio,
        @Schema(description = "Categoría del lineamiento.", example = "backend")
        String categoria,
        @Schema(description = "Etiquetas de búsqueda.", example = "[\"spring-boot\", \"observabilidad\"]")
        List<String> tags,
        @Schema(description = "Fecha de creación del documento.")
        LocalDateTime fechaCreacion,
        @Schema(description = "Fecha de la última actualización.")
        LocalDateTime fechaActualizacion
) {

    public static DocumentoLineamientoResponse from(DocumentoLineamiento documento) {
        return new DocumentoLineamientoResponse(
                documento.getId(),
                documento.getTitulo(),
                documento.getContenido(),
                documento.getProyectoId(),
                documento.getTipoDocumento(),
                documento.getEstado(),
                documento.getLineamiento(),
                documento.getDominio(),
                documento.getCategoria(),
                documento.getTags(),
                documento.getFechaCreacion(),
                documento.getFechaActualizacion()
        );
    }
}

