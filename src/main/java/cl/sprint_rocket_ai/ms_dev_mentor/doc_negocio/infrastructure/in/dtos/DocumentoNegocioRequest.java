package cl.sprint_rocket_ai.ms_dev_mentor.doc_negocio.infrastructure.in.dtos;

import cl.sprint_rocket_ai.ms_dev_mentor.commons.domain.enums.EstadoDocumento;
import cl.sprint_rocket_ai.ms_dev_mentor.doc_negocio.domain.enums.FuenteDocumento;
import cl.sprint_rocket_ai.ms_dev_mentor.doc_negocio.domain.models.DocumentoNegocio;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

@Schema(description = "DTO para la creación/actualización de un Documento de Negocio.")
public record DocumentoNegocioRequest(
        @NotBlank(message = "El título es obligatorio")
        @Size(max = 200, message = "El título no puede superar los 200 caracteres")
        @Schema(description = "Título del documento.", example = "Especificación de Requisitos de Usuario")
        String titulo,

        @NotBlank(message = "El contenido es obligatorio")
        @Schema(description = "Contenido del documento en formato Markdown o texto plano.",
                example = "El sistema debe permitir a los usuarios registrarse con su correo electrónico.")
        String contenido,

        @NotBlank(message = "El proyectoId es obligatorio")
        @Schema(description = "ID del proyecto al que pertenece el documento.", example = "6653d50711312d1174a61516")
        String proyectoId,

        @NotNull(message = "El estado es obligatorio")
        @Schema(description = "Estado actual del documento.", implementation = EstadoDocumento.class, example = "BORRADOR")
        EstadoDocumento estado,

        @NotNull(message = "La fuente es obligatoria")
        @Schema(description = "Origen del documento.", implementation = FuenteDocumento.class, example = "CONFLUENCE")
        FuenteDocumento fuente,

        @Schema(description = "URL de la fuente original (si aplica).",
                example = "https://confluence.empresa.cl/pages/viewpage.action?pageId=12345")
        String urlFuente,

        @Schema(description = "Etiquetas asociadas al documento.", example = "[\"pagos\",\"onboarding\"]")
        List<String> tags,

        @Schema(description = "Criterios de aceptación.",
                example = "[\"El usuario debe poder registrarse con email\",\"Se debe validar el correo\"]")
        List<String> criteriosAceptacion,

        @Schema(description = "Resumen ejecutivo del documento.",
                example = "Define los requisitos funcionales del módulo de registro.")
        String resumen
) {
    public void applyTo(DocumentoNegocio target) {
        target.setTitulo(titulo);
        target.setContenido(contenido);
        target.setProyectoId(proyectoId);
        target.setEstado(estado);
        target.setFuente(fuente);
        target.setUrlFuente(urlFuente);
        target.setTags(tags);
        target.setCriteriosAceptacion(criteriosAceptacion);
        target.setResumen(resumen);
    }
}
