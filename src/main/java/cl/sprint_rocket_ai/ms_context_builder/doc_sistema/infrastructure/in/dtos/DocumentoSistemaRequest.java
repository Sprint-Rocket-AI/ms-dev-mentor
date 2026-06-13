package cl.sprint_rocket_ai.ms_context_builder.doc_sistema.infrastructure.in.dtos;

import cl.sprint_rocket_ai.ms_context_builder.commons.domain.enums.TipoDocumento;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

@Schema(
    description = "DTO para la creación de un nuevo Documento Sistema.",
    example = """
            {
              "titulo": "Diseño de la Arquitectura de Microservicios",
              "contenido": "El servicio de usuarios se comunicará con el servicio de pedidos a través de eventos asíncronos.",
              "tipo": "SISTEMA",
              "urlRepos": ["https://github.com/empresa/usuarios-service"],
              "stack": ["spring-boot", "mongodb"],
              "devs": ["ana.perez", "carlos.rojas"]
            }
            """
)
public record DocumentoSistemaRequest(
    @NotBlank(message = "El título es obligatorio")
    @Size(max = 200, message = "El título no puede superar los 200 caracteres")
    @Schema(description = "Título del documento.", example = "Diseño de la Arquitectura de Microservicios")
    String titulo,

    @NotBlank(message = "El contenido es obligatorio")
    @Schema(description = "Contenido del documento en formato Markdown o texto plano.", example = "El servicio de usuarios se comunicará con el servicio de pedidos a través de eventos...")
    String contenido,

    @Schema(description = "URLs de repositorios asociados al documento.", example = "[\"https://github.com/empresa/usuarios-service\"]")
    List<String> urlRepos,

    @Schema(description = "Stack tecnologico del sistema.", example = "[\"spring-boot\", \"mongodb\"]")
    List<String> stack,

    @Schema(description = "Equipo de desarrollo asociado.", example = "[\"ana.perez\", \"carlos.rojas\"]")
    List<String> devs
) {
}
