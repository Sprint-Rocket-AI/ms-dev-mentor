package cl.sprint_rocket_ai.ms_dev_mentor.doc_lineamiento.infrastructure.in;

import cl.sprint_rocket_ai.ms_dev_mentor.doc_lineamiento.application.GetDocumentoLineamientoById;
import cl.sprint_rocket_ai.ms_dev_mentor.doc_lineamiento.application.SaveDocumentoLineamiento;
import cl.sprint_rocket_ai.ms_dev_mentor.doc_lineamiento.application.UpdateDocumentoLineamiento;
import cl.sprint_rocket_ai.ms_dev_mentor.doc_lineamiento.infrastructure.in.dtos.DocumentoLineamientoRequest;
import cl.sprint_rocket_ai.ms_dev_mentor.doc_lineamiento.infrastructure.in.dtos.DocumentoLineamientoResponse;
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
@RequestMapping("/api/documentos-lineamientos")
public final class DocumentoLineamientoController implements DocumentoLineamientoRest {

    private final SaveDocumentoLineamiento saveDocumentoLineamiento;
    private final GetDocumentoLineamientoById getDocumentoLineamientoById;
    private final UpdateDocumentoLineamiento updateDocumentoLineamiento;

    public DocumentoLineamientoController(SaveDocumentoLineamiento saveDocumentoLineamiento,
                                          GetDocumentoLineamientoById getDocumentoLineamientoById,
                                          UpdateDocumentoLineamiento updateDocumentoLineamiento) {
        this.saveDocumentoLineamiento = saveDocumentoLineamiento;
        this.getDocumentoLineamientoById = getDocumentoLineamientoById;
        this.updateDocumentoLineamiento = updateDocumentoLineamiento;
    }

    @Override
    @PostMapping
    public ResponseEntity<DocumentoLineamientoResponse> create(@Valid @RequestBody DocumentoLineamientoRequest request) {
        return new ResponseEntity<>(saveDocumentoLineamiento.execute(request), HttpStatus.CREATED);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<DocumentoLineamientoResponse> getById(@PathVariable String id) {
        return ResponseEntity.ok(getDocumentoLineamientoById.execute(id));
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<DocumentoLineamientoResponse> update(@PathVariable String id,
                                                               @Valid @RequestBody DocumentoLineamientoRequest request) {
        return ResponseEntity.ok(updateDocumentoLineamiento.execute(id, request));
    }
}

