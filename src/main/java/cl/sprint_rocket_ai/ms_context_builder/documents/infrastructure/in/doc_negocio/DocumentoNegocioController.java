package cl.sprint_rocket_ai.ms_context_builder.documents.infrastructure.in.doc_negocio;

import cl.sprint_rocket_ai.ms_context_builder.documents.application.doc_negocio.SaveDocumentoNegocio;
import cl.sprint_rocket_ai.ms_context_builder.documents.application.doc_negocio.UpdateDocumentoNegocio;
import cl.sprint_rocket_ai.ms_context_builder.documents.infrastructure.in.doc_negocio.dtos.DocumentoNegocioRequest;
import cl.sprint_rocket_ai.ms_context_builder.documents.infrastructure.in.doc_negocio.dtos.DocumentoNegocioResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/documentos-negocio")
public final class DocumentoNegocioController implements DocumentoNegocioRest {

    private final SaveDocumentoNegocio saveDocumentoNegocio;
    private final UpdateDocumentoNegocio updateDocumentoNegocio;

    public DocumentoNegocioController(SaveDocumentoNegocio saveDocumentoNegocio,
                                      UpdateDocumentoNegocio updateDocumentoNegocio) {
        this.saveDocumentoNegocio = saveDocumentoNegocio;
        this.updateDocumentoNegocio = updateDocumentoNegocio;
    }

    @Override
    @PostMapping
    public ResponseEntity<DocumentoNegocioResponse> create(@Valid @RequestBody DocumentoNegocioRequest request) {
        return new ResponseEntity<>(saveDocumentoNegocio.execute(request), HttpStatus.CREATED);
    }


    @Override
    @PutMapping("/{id}")
    public ResponseEntity<DocumentoNegocioResponse> update(@PathVariable String id, @Valid @RequestBody DocumentoNegocioRequest request) {
        return ResponseEntity.ok(updateDocumentoNegocio.execute(id, request));
    }
}
