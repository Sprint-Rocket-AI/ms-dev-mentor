package cl.sprint_rocket_ai.ms_context_builder.documents.infrastructure.in.doc_ddl;

import cl.sprint_rocket_ai.ms_context_builder.documents.application.doc_ddl.SaveDocumentoDDL;
import cl.sprint_rocket_ai.ms_context_builder.documents.application.doc_ddl.UpdateDocumentoDDL;
import cl.sprint_rocket_ai.ms_context_builder.documents.infrastructure.in.doc_ddl.dtos.DocumentoDDLRequest;
import cl.sprint_rocket_ai.ms_context_builder.documents.infrastructure.in.doc_ddl.dtos.DocumentoDDLResponse;
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
@RequestMapping("/api/documentos-ddl")
public final class DocumentoDDLController implements DocumentoDDLRest {

    private final SaveDocumentoDDL saveDocumentoDDL;
    private final UpdateDocumentoDDL updateDocumentoDDL;

    public DocumentoDDLController(SaveDocumentoDDL saveDocumentoDDL,
                                  UpdateDocumentoDDL updateDocumentoDDL) {
        this.saveDocumentoDDL = saveDocumentoDDL;
        this.updateDocumentoDDL = updateDocumentoDDL;
    }

    @Override
    @PostMapping
    public ResponseEntity<DocumentoDDLResponse> create(@Valid @RequestBody DocumentoDDLRequest request) {
        return new ResponseEntity<>(saveDocumentoDDL.execute(request), HttpStatus.CREATED);
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<DocumentoDDLResponse> update(@PathVariable String id,
                                                       @Valid @RequestBody DocumentoDDLRequest request) {
        return ResponseEntity.ok(updateDocumentoDDL.execute(id, request));
    }
}

