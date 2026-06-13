package cl.sprint_rocket_ai.ms_dev_mentor.doc_ddl.infrastructure.in;

import cl.sprint_rocket_ai.ms_dev_mentor.doc_ddl.application.GetDocumentoDDLById;
import cl.sprint_rocket_ai.ms_dev_mentor.doc_ddl.application.SaveDocumentoDDL;
import cl.sprint_rocket_ai.ms_dev_mentor.doc_ddl.application.UpdateDocumentoDDL;
import cl.sprint_rocket_ai.ms_dev_mentor.doc_ddl.infrastructure.in.dtos.DocumentoDDLRequest;
import cl.sprint_rocket_ai.ms_dev_mentor.doc_ddl.infrastructure.in.dtos.DocumentoDDLResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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
    private final GetDocumentoDDLById getDocumentoDDLById;
    private final UpdateDocumentoDDL updateDocumentoDDL;

    public DocumentoDDLController(SaveDocumentoDDL saveDocumentoDDL,
                                  GetDocumentoDDLById getDocumentoDDLById,
                                  UpdateDocumentoDDL updateDocumentoDDL) {
        this.saveDocumentoDDL = saveDocumentoDDL;
        this.getDocumentoDDLById = getDocumentoDDLById;
        this.updateDocumentoDDL = updateDocumentoDDL;
    }

    @Override
    @PostMapping
    public ResponseEntity<DocumentoDDLResponse> create(@Valid @RequestBody DocumentoDDLRequest request) {
        return new ResponseEntity<>(saveDocumentoDDL.execute(request), HttpStatus.CREATED);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<DocumentoDDLResponse> getById(@PathVariable String id) {
        return ResponseEntity.ok(getDocumentoDDLById.execute(id));
    }


    @Override
    @PutMapping("/{id}")
    public ResponseEntity<DocumentoDDLResponse> update(@PathVariable String id,
                                                       @Valid @RequestBody DocumentoDDLRequest request) {
        return ResponseEntity.ok(updateDocumentoDDL.execute(id, request));
    }
}

