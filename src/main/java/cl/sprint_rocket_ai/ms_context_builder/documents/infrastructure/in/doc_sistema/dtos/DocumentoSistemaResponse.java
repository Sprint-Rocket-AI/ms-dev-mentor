package cl.sprint_rocket_ai.ms_context_builder.documents.infrastructure.in.doc_sistema.dtos;

import cl.sprint_rocket_ai.ms_context_builder.documents.domain.enums.TipoDocumento;
import cl.sprint_rocket_ai.ms_context_builder.documents.domain.models.DocumentoSistema;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;
import java.util.List;

@Schema(description = "DTO para la respuesta de un Documento Sistema.")
public record DocumentoSistemaResponse(
    @Schema(description = "ID único del documento.", example = "6653d50711312d1174a61517")
    String id,
    @Schema(description = "Título del documento.", example = "Diseño de la Arquitectura")
    String titulo,
    @Schema(description = "Contenido del documento.", example = "El sistema seguirá un patrón de microservicios...")
    String contenido,
    @Schema(description = "Tipo de documento.", implementation = TipoDocumento.class)
    TipoDocumento tipo,
    @Schema(description = "URLs de repositorios asociados al documento.")
    List<String> urlRepos,
    @Schema(description = "Stack tecnologico del sistema.")
    List<String> stack,
    @Schema(description = "Equipo de desarrollo asociado.")
    List<String> devs,
    @Schema(description = "Fecha de creación del documento.")
    LocalDateTime fechaCreacion,
    @Schema(description = "Fecha de la última actualización del documento.")
    LocalDateTime fechaActualizacion
) {
    public static DocumentoSistemaResponse from(DocumentoSistema documento) {
        return new DocumentoSistemaResponse(
                documento.getId(),
                documento.getTitulo(),
                documento.getContenido(),
                documento.getTipoDocumento(),
                documento.getUrlRepos(),
                documento.getStack(),
                documento.getDevs(),
                documento.getFechaCreacion(),
                documento.getFechaActualizacion()
        );
    }
}
