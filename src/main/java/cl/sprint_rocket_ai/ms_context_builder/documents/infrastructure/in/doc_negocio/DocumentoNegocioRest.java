package cl.sprint_rocket_ai.ms_context_builder.documents.infrastructure.in.doc_negocio;

import cl.sprint_rocket_ai.ms_context_builder.documents.infrastructure.in.doc_negocio.dtos.DocumentoNegocioRequest;
import cl.sprint_rocket_ai.ms_context_builder.documents.infrastructure.in.doc_negocio.dtos.DocumentoNegocioResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;


@Tag(name = "Documentos de Negocio", description = "API para la gestión de documentos de negocio.")
public interface DocumentoNegocioRest {

    @Operation(summary = "Crear un nuevo documento de negocio")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Documento creado exitosamente",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = DocumentoNegocioResponse.class))),
            @ApiResponse(responseCode = "400", description = "Solicitud inválida")
    })
    ResponseEntity<DocumentoNegocioResponse> create(@RequestBody DocumentoNegocioRequest request);

    @Operation(summary = "Actualizar un documento de negocio existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Documento actualizado exitosamente",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = DocumentoNegocioResponse.class))),
            @ApiResponse(responseCode = "404", description = "Documento no encontrado")
    })
    ResponseEntity<DocumentoNegocioResponse> update(@Parameter(description = "ID del documento a actualizar") @PathVariable String id,
                                                    @RequestBody DocumentoNegocioRequest request);
}
