package cl.sprint_rocket_ai.ms_dev_mentor.doc_sistema.infrastructure.in;

import cl.sprint_rocket_ai.ms_dev_mentor.doc_sistema.application.GetDocumentoSistemaById;
import cl.sprint_rocket_ai.ms_dev_mentor.doc_sistema.application.SaveDocumentoSistema;
import cl.sprint_rocket_ai.ms_dev_mentor.doc_sistema.application.UpdateDocumentoSistema;
import cl.sprint_rocket_ai.ms_dev_mentor.doc_sistema.infrastructure.in.dtos.DocumentoSistemaRequest;
import cl.sprint_rocket_ai.ms_dev_mentor.doc_sistema.infrastructure.in.dtos.DocumentoSistemaResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/documentos-sistemas")
public final class DocumentoSistemaController implements DocumentoSistemaRest {

    private final SaveDocumentoSistema saveDocumentoSistema;
    private final GetDocumentoSistemaById getDocumentoSistemaById;
    private final UpdateDocumentoSistema updateDocumentoSistema;

    public DocumentoSistemaController(SaveDocumentoSistema saveDocumentoSistema,
                                      GetDocumentoSistemaById getDocumentoSistemaById,
                                      UpdateDocumentoSistema updateDocumentoSistema) {
        this.saveDocumentoSistema = saveDocumentoSistema;
        this.getDocumentoSistemaById = getDocumentoSistemaById;
        this.updateDocumentoSistema = updateDocumentoSistema;
    }

    @Override
    @PostMapping
    public ResponseEntity<DocumentoSistemaResponse> create(@Valid @RequestBody DocumentoSistemaRequest request) {
        return new ResponseEntity<>(saveDocumentoSistema.execute(request), HttpStatus.CREATED);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<DocumentoSistemaResponse> getById(@PathVariable String id) {
        return ResponseEntity.ok(getDocumentoSistemaById.execute(id));
    }


    @Override
    @PutMapping("/{id}")
    public ResponseEntity<DocumentoSistemaResponse> update(@PathVariable String id, @Valid @RequestBody DocumentoSistemaRequest request) {
        return ResponseEntity.ok(updateDocumentoSistema.execute(id, request));

    }
}
