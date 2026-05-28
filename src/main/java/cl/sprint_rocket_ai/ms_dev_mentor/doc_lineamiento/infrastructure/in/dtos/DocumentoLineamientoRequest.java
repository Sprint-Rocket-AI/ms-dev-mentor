package cl.sprint_rocket_ai.ms_dev_mentor.doc_lineamiento.infrastructure.in.dtos;

import cl.sprint_rocket_ai.ms_dev_mentor.commons.domain.enums.EstadoDocumento;
import cl.sprint_rocket_ai.ms_dev_mentor.doc_lineamiento.domain.models.DocumentoLineamiento;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

@Schema(description = "DTO para la creación o actualización de un Documento Lineamiento.")
public record DocumentoLineamientoRequest(
        @NotBlank(message = "El título es obligatorio")
        @Size(max = 200, message = "El título no puede superar los 200 caracteres")
        @Schema(description = "Título del lineamiento.", example = "Lineamiento de diseño de microservicios")
        String titulo,

        @NotBlank(message = "El contenido es obligatorio")
        @Schema(description = "Contenido extendido del lineamiento.", example = "Cada microservicio debe ser autónomo y desplegable de forma independiente...")
        String contenido,

        @NotBlank(message = "El proyectoId es obligatorio")
        @Schema(description = "ID del proyecto al que pertenece el documento.", example = "6653d50711312d1174a61516")
        String proyectoId,

        @NotNull(message = "El estado es obligatorio")
        @Schema(description = "Estado actual del documento.", implementation = EstadoDocumento.class, example = "BORRADOR")
        EstadoDocumento estado,

        @NotBlank(message = "El lineamiento es obligatorio")
        @Schema(description = "Núcleo del conocimiento que será usado por el sistema RAG.", example = "Todo microservicio debe exponer un health check en /actuator/health.")
        String lineamiento,

        @NotBlank(message = "El dominio es obligatorio")
        @Schema(description = "Dominio al que aplica el lineamiento.", example = "microservicios")
        String dominio,

        @NotBlank(message = "La categoría es obligatoria")
        @Schema(description = "Categoría del lineamiento.", example = "backend")
        String categoria,

        @Schema(description = "Etiquetas de búsqueda asociadas al lineamiento.", example = "[\"spring-boot\", \"observabilidad\"]")
        List<String> tags
) {

    public void applyTo(DocumentoLineamiento target) {
        target.setTitulo(titulo);
        target.setContenido(contenido);
        target.setProyectoId(proyectoId);
        target.setEstado(estado);
        target.setLineamiento(lineamiento);
        target.setDominio(dominio);
        target.setCategoria(categoria);
        target.setTags(tags);
    }
}
