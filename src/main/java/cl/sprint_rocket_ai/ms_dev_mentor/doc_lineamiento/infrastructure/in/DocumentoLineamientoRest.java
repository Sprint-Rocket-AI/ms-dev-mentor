package cl.sprint_rocket_ai.ms_dev_mentor.doc_lineamiento.infrastructure.in;

import cl.sprint_rocket_ai.ms_dev_mentor.doc_lineamiento.infrastructure.in.dtos.DocumentoLineamientoRequest;
import cl.sprint_rocket_ai.ms_dev_mentor.doc_lineamiento.infrastructure.in.dtos.DocumentoLineamientoResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

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

    @Operation(summary = "Obtener un documento lineamiento por su ID")
    @ApiResponse(
            responseCode = "200",
            description = "Documento encontrado",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = DocumentoLineamientoResponse.class))
    )
    @ApiResponse(responseCode = "404", description = "Documento no encontrado")
    ResponseEntity<DocumentoLineamientoResponse> getById(
            @Parameter(description = "ID del documento a obtener") String id
    );

    @Operation(summary = "Listar todos los documentos lineamiento de un proyecto")
    @ApiResponse(
            responseCode = "200",
            description = "Listado de documentos",
            content = @Content(mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = DocumentoLineamientoResponse.class))))
    @ApiResponse(responseCode = "400", description = "Proyecto inválido")
    ResponseEntity<List<DocumentoLineamientoResponse>> getByProyectoId(
            @Parameter(description = "ID del proyecto para listar sus documentos") String proyectoId
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

