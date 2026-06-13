package cl.sprint_rocket_ai.ms_context_builder.doc_lineamiento.infrastructure.in.dtos;

import cl.sprint_rocket_ai.ms_context_builder.doc_lineamiento.domain.models.DocumentoLineamiento;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.List;

@Schema(
        description = "DTO para la creación o actualización de un Documento Lineamiento.",
        example = """
                {
                  "titulo": "Lineamiento de diseño de microservicios",
                  "contenido": "Cada microservicio debe ser autónomo y desplegable de forma independiente.",
                  "tags": ["spring-boot", "observabilidad"]
                }
                """
)
public record DocumentoLineamientoRequest(
        @NotBlank(message = "El título es obligatorio")
        @Size(max = 200, message = "El título no puede superar los 200 caracteres")
        @Schema(description = "Título del lineamiento.", example = "Lineamiento de diseño de microservicios")
        String titulo,

        @NotBlank(message = "El contenido es obligatorio")
        @Schema(description = "Contenido extendido del lineamiento.", example = "Cada microservicio debe ser autónomo y desplegable de forma independiente...")
        String contenido,

        @Schema(description = "Etiquetas de búsqueda asociadas al lineamiento.", example = "[\"spring-boot\", \"observabilidad\"]")
        List<String> tags
) {

    public void applyTo(DocumentoLineamiento target) {
        target.setTitulo(titulo);
        target.setContenido(contenido);
        target.setTags(tags);
    }
}
