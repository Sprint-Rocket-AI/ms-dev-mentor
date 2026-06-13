package cl.sprint_rocket_ai.ms_context_builder.documents.infrastructure.in.doc_lineamiento;

import cl.sprint_rocket_ai.ms_context_builder.documents.application.doc_lineamiento.SaveDocumentoLineamiento;
import cl.sprint_rocket_ai.ms_context_builder.documents.application.doc_lineamiento.UpdateDocumentoLineamiento;
import cl.sprint_rocket_ai.ms_context_builder.documents.infrastructure.in.doc_lineamiento.dtos.DocumentoLineamientoRequest;
import cl.sprint_rocket_ai.ms_context_builder.documents.infrastructure.in.doc_lineamiento.dtos.DocumentoLineamientoResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/documentos-lineamientos")
public final class DocumentoLineamientoController implements DocumentoLineamientoRest {

    private final SaveDocumentoLineamiento saveDocumentoLineamiento;
    private final UpdateDocumentoLineamiento updateDocumentoLineamiento;

    public DocumentoLineamientoController(SaveDocumentoLineamiento saveDocumentoLineamiento,
                                          UpdateDocumentoLineamiento updateDocumentoLineamiento) {
        this.saveDocumentoLineamiento = saveDocumentoLineamiento;
        this.updateDocumentoLineamiento = updateDocumentoLineamiento;
    }

    @Override
    @PostMapping
    public ResponseEntity<DocumentoLineamientoResponse> create(@Valid @RequestBody DocumentoLineamientoRequest request) {
        return new ResponseEntity<>(saveDocumentoLineamiento.execute(request), HttpStatus.CREATED);
    }


    @Override
    @PutMapping("/{id}")
    public ResponseEntity<DocumentoLineamientoResponse> update(@PathVariable String id,
                                                               @Valid @RequestBody DocumentoLineamientoRequest request) {
        return ResponseEntity.ok(updateDocumentoLineamiento.execute(id, request));
    }
}

