package cl.sprint_rocket_ai.ms_context_builder.documents.infrastructure.in.doc_sistema;

import cl.sprint_rocket_ai.ms_context_builder.documents.application.doc_sistema.SaveDocumentoSistema;
import cl.sprint_rocket_ai.ms_context_builder.documents.application.doc_sistema.UpdateDocumentoSistema;
import cl.sprint_rocket_ai.ms_context_builder.documents.infrastructure.in.doc_sistema.dtos.DocumentoSistemaRequest;
import cl.sprint_rocket_ai.ms_context_builder.documents.infrastructure.in.doc_sistema.dtos.DocumentoSistemaResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/documentos-sistemas")
public final class DocumentoSistemaController implements DocumentoSistemaRest {

    private final SaveDocumentoSistema saveDocumentoSistema;
    private final UpdateDocumentoSistema updateDocumentoSistema;

    public DocumentoSistemaController(SaveDocumentoSistema saveDocumentoSistema,
                                      UpdateDocumentoSistema updateDocumentoSistema) {
        this.saveDocumentoSistema = saveDocumentoSistema;
        this.updateDocumentoSistema = updateDocumentoSistema;
    }

    @Override
    @PostMapping
    public ResponseEntity<DocumentoSistemaResponse> create(@Valid @RequestBody DocumentoSistemaRequest request) {
        return new ResponseEntity<>(saveDocumentoSistema.execute(request), HttpStatus.CREATED);
    }


    @Override
    @PutMapping("/{id}")
    public ResponseEntity<DocumentoSistemaResponse> update(@PathVariable String id, @Valid @RequestBody DocumentoSistemaRequest request) {
        return ResponseEntity.ok(updateDocumentoSistema.execute(id, request));

    }
}
