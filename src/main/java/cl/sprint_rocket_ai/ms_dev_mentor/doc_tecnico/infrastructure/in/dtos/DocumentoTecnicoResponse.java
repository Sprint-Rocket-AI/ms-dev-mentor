package cl.sprint_rocket_ai.ms_dev_mentor.doc_tecnico.infrastructure.in.dtos;

import cl.sprint_rocket_ai.ms_dev_mentor.commons.domain.enums.EstadoDocumento;
import cl.sprint_rocket_ai.ms_dev_mentor.commons.domain.enums.TipoDocumento;
import cl.sprint_rocket_ai.ms_dev_mentor.doc_tecnico.domain.models.DocumentoTecnico;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

@Schema(description = "DTO para la respuesta de un Documento Técnico.")
public record DocumentoTecnicoResponse(
    @Schema(description = "ID único del documento.", example = "6653d50711312d1174a61517")
    String id,
    @Schema(description = "Título del documento.", example = "Diseño de la Arquitectura")
    String titulo,
    @Schema(description = "Contenido del documento.", example = "El sistema seguirá un patrón de microservicios...")
    String contenido,
    @Schema(description = "ID del proyecto al que pertenece.", example = "6653d50711312d1174a61516")
    String proyectoId,
    @Schema(description = "Tipo de documento.", implementation = TipoDocumento.class)
    TipoDocumento tipo,
    @Schema(description = "Estado del documento.", implementation = EstadoDocumento.class)
    EstadoDocumento estado,
    @Schema(description = "Fecha de creación del documento.")
    LocalDateTime fechaCreacion,
    @Schema(description = "Fecha de la última actualización del documento.")
    LocalDateTime fechaActualizacion
) {
    public static DocumentoTecnicoResponse from(DocumentoTecnico documento) {
        return new DocumentoTecnicoResponse(
                documento.getId(),
                documento.getTitulo(),
                documento.getContenido(),
                documento.getProyectoId(),
                documento.getTipoDocumento(),
                documento.getEstado(),
                documento.getFechaCreacion(),
                documento.getFechaActualizacion()
        );
    }
}
