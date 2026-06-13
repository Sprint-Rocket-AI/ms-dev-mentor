package cl.sprint_rocket_ai.ms_context_builder.documents.infrastructure.in.doc_lineamiento;

import cl.sprint_rocket_ai.ms_context_builder.documents.infrastructure.in.doc_lineamiento.dtos.DocumentoLineamientoRequest;
import cl.sprint_rocket_ai.ms_context_builder.documents.infrastructure.in.doc_lineamiento.dtos.DocumentoLineamientoResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;


@Tag(
        name = "Documentos Lineamientos",
        description = "API para la gestión de documentos de lineamientos (base de conocimiento para RAG)."
)
public interface DocumentoLineamientoRest {

    @Operation(summary = "Crear un nuevo documento lineamiento")
    @ApiResponse(
            responseCode = "201",
            description = "Documento creado exitosamente",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = DocumentoLineamientoResponse.class))
    )
    @ApiResponse(responseCode = "400", description = "Solicitud inválida")
    ResponseEntity<DocumentoLineamientoResponse> create(
            @RequestBody DocumentoLineamientoRequest request
    );


    @Operation(summary = "Actualizar un documento lineamiento existente")
    @ApiResponse(
            responseCode = "200",
            description = "Documento actualizado exitosamente",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = DocumentoLineamientoResponse.class))
    )
    @ApiResponse(responseCode = "400", description = "Solicitud inválida")
    @ApiResponse(responseCode = "404", description = "Documento no encontrado")
    ResponseEntity<DocumentoLineamientoResponse> update(
            @Parameter(description = "ID del documento a actualizar") String id,
            @RequestBody DocumentoLineamientoRequest request
    );
}

