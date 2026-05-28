package cl.sprint_rocket_ai.ms_dev_mentor.doc_sistema.infrastructure.in.dtos;

import cl.sprint_rocket_ai.ms_dev_mentor.commons.domain.enums.EstadoDocumento;
import cl.sprint_rocket_ai.ms_dev_mentor.commons.domain.enums.TipoDocumento;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Schema(description = "DTO para la creación de un nuevo Documento Sistema.")
public record DocumentoSistemaRequest(
    @NotBlank(message = "El título es obligatorio")
    @Size(max = 200, message = "El título no puede superar los 200 caracteres")
    @Schema(description = "Título del documento.", example = "Diseño de la Arquitectura de Microservicios")
    String titulo,
    @NotBlank(message = "El contenido es obligatorio")
    @Schema(description = "Contenido del documento en formato Markdown o texto plano.", example = "El servicio de usuarios se comunicará con el servicio de pedidos a través de eventos...")
    String contenido,
    @NotBlank(message = "El proyectoId es obligatorio")
    @Schema(description = "ID del proyecto al que pertenece el documento.", example = "6653d50711312d1174a61516")
    String proyectoId,
    @NotNull(message = "El tipo es obligatorio")
    @Schema(description = "Tipo de documento.", implementation = TipoDocumento.class, example = "SISTEMA")
    TipoDocumento tipo,
    @NotNull(message = "El estado es obligatorio")
    @Schema(description = "Estado actual del documento.", implementation = EstadoDocumento.class, example = "BORRADOR")
    EstadoDocumento estado
) {
}
