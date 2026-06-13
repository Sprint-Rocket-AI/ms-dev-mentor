package cl.sprint_rocket_ai.ms_context_builder.documents.infrastructure.in.doc_ddl;

import cl.sprint_rocket_ai.ms_context_builder.documents.infrastructure.in.doc_ddl.dtos.DocumentoDDLRequest;
import cl.sprint_rocket_ai.ms_context_builder.documents.infrastructure.in.doc_ddl.dtos.DocumentoDDLResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;


@Tag(
        name = "Documentos DDL",
        description = "API para la gestión de documentos DDL (modelos de datos)."
)
public interface DocumentoDDLRest {

    @Operation(summary = "Crear un nuevo documento DDL")
    @ApiResponse(
            responseCode = "201",
            description = "Documento creado exitosamente",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = DocumentoDDLResponse.class))
    )
    @ApiResponse(responseCode = "400", description = "Solicitud inválida")
    ResponseEntity<DocumentoDDLResponse> create(
            @RequestBody DocumentoDDLRequest request
    );


    @Operation(summary = "Actualizar un documento DDL existente")
    @ApiResponse(
            responseCode = "200",
            description = "Documento actualizado exitosamente",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = DocumentoDDLResponse.class))
    )
    @ApiResponse(responseCode = "400", description = "Solicitud inválida")
    @ApiResponse(responseCode = "404", description = "Documento no encontrado")
    ResponseEntity<DocumentoDDLResponse> update(
            @Parameter(description = "ID del documento a actualizar") String id,
            @RequestBody DocumentoDDLRequest request
    );
}

