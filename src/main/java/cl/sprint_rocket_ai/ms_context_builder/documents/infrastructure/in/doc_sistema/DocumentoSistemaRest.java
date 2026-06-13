package cl.sprint_rocket_ai.ms_context_builder.documents.infrastructure.in.doc_sistema;

import cl.sprint_rocket_ai.ms_context_builder.documents.infrastructure.in.doc_sistema.dtos.DocumentoSistemaRequest;
import cl.sprint_rocket_ai.ms_context_builder.documents.infrastructure.in.doc_sistema.dtos.DocumentoSistemaResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;


@Tag(
		name = "Documentos Sistemas",
		description = "API para la gestión de documentos sistemas."
)
public interface DocumentoSistemaRest {

	@Operation(
			summary = "Crear un nuevo documento sistema"
	)
	@ApiResponse(
			responseCode = "201",
			description = "Documento creado exitosamente",
			content = @Content(mediaType = "application/json",
					schema = @Schema(implementation = DocumentoSistemaResponse.class))
	)
	@ApiResponse(responseCode = "400", description = "Solicitud inválida")
	ResponseEntity<DocumentoSistemaResponse> create(
			@RequestBody DocumentoSistemaRequest request
	);

	@Operation(
			summary = "Actualizar un documento sistema existente"
	)
	@ApiResponse(
			responseCode = "200",
			description = "Documento actualizado exitosamente",
			content = @Content(mediaType = "application/json",
					schema = @Schema(implementation = DocumentoSistemaResponse.class))
	)
	@ApiResponse(responseCode = "404", description = "Documento no encontrado")
	ResponseEntity<DocumentoSistemaResponse> update(@Parameter(description = "ID del documento a actualizar") String id,
													@RequestBody DocumentoSistemaRequest request);

}
