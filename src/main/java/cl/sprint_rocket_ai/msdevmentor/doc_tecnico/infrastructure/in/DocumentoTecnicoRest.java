package cl.sprint_rocket_ai.msdevmentor.doc_tecnico.infrastructure.in;

import cl.sprint_rocket_ai.msdevmentor.doc_tecnico.infrastructure.in.dtos.DocumentoTecnicoRequest;
import cl.sprint_rocket_ai.msdevmentor.doc_tecnico.infrastructure.in.dtos.DocumentoTecnicoResponse;
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
		name = "Documentos Técnicos",
		description = "API para la gestión de documentos técnicos."
)
public interface DocumentoTecnicoRest {

	@Operation(
			summary = "Crear un nuevo documento técnico"
	)
	@ApiResponse(
			responseCode = "201",
			description = "Documento creado exitosamente",
			content = @Content(mediaType = "application/json",
					schema = @Schema(implementation = DocumentoTecnicoResponse.class))
	)
	@ApiResponse(responseCode = "400", description = "Solicitud inválida")
	ResponseEntity<DocumentoTecnicoResponse> create(
			@RequestBody DocumentoTecnicoRequest request
	);

	@Operation(
			summary = "Obtener un documento técnico por su ID"
	)
	@ApiResponse(
			responseCode = "200",
			description = "Documento encontrado",
			content = @Content(mediaType = "application/json",
					schema = @Schema(implementation = DocumentoTecnicoResponse.class))
	)
	@ApiResponse(responseCode = "404", description = "Documento no encontrado")
	ResponseEntity<DocumentoTecnicoResponse> getById(@Parameter(description = "ID del documento a obtener") String id);

	@Operation(
			summary = "Listar todos los documentos técnicos de un proyecto"
	)
	@ApiResponse(
			responseCode = "200",
			description = "Listado de documentos",
			content = @Content(mediaType = "application/json",
					array = @ArraySchema(schema = @Schema(implementation = DocumentoTecnicoResponse.class))))
	@ApiResponse(responseCode = "400", description = "Proyecto inválido")
	ResponseEntity<List<DocumentoTecnicoResponse>> getByProyectoId(@Parameter(description = "ID del proyecto para listar sus documentos") String proyectoId);

	@Operation(
			summary = "Actualizar un documento técnico existente"
	)
	@ApiResponse(
			responseCode = "200",
			description = "Documento actualizado exitosamente",
			content = @Content(mediaType = "application/json",
					schema = @Schema(implementation = DocumentoTecnicoResponse.class))
	)
	@ApiResponse(responseCode = "404", description = "Documento no encontrado")
	ResponseEntity<DocumentoTecnicoResponse> update(@Parameter(description = "ID del documento a actualizar") String id,
													@RequestBody DocumentoTecnicoRequest request);

}
