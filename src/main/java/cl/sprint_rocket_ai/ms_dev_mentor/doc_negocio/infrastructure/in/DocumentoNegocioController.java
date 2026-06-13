package cl.sprint_rocket_ai.ms_dev_mentor.doc_negocio.infrastructure.in;

import cl.sprint_rocket_ai.ms_dev_mentor.doc_negocio.application.GetDocumentoNegocioById;
import cl.sprint_rocket_ai.ms_dev_mentor.doc_negocio.application.SaveDocumentoNegocio;
import cl.sprint_rocket_ai.ms_dev_mentor.doc_negocio.application.UpdateDocumentoNegocio;
import cl.sprint_rocket_ai.ms_dev_mentor.doc_negocio.infrastructure.in.dtos.DocumentoNegocioRequest;
import cl.sprint_rocket_ai.ms_dev_mentor.doc_negocio.infrastructure.in.dtos.DocumentoNegocioResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/documentos-negocio")
public final class DocumentoNegocioController implements DocumentoNegocioRest {

    private final SaveDocumentoNegocio saveDocumentoNegocio;
    private final GetDocumentoNegocioById getDocumentoNegocioById;
    private final UpdateDocumentoNegocio updateDocumentoNegocio;

    public DocumentoNegocioController(SaveDocumentoNegocio saveDocumentoNegocio,
                                      GetDocumentoNegocioById getDocumentoNegocioById,
                                      UpdateDocumentoNegocio updateDocumentoNegocio) {
        this.saveDocumentoNegocio = saveDocumentoNegocio;
        this.getDocumentoNegocioById = getDocumentoNegocioById;
        this.updateDocumentoNegocio = updateDocumentoNegocio;
    }

    @Override
    @PostMapping
    public ResponseEntity<DocumentoNegocioResponse> create(@Valid @RequestBody DocumentoNegocioRequest request) {
        return new ResponseEntity<>(saveDocumentoNegocio.execute(request), HttpStatus.CREATED);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<DocumentoNegocioResponse> getById(@PathVariable String id) {
        return ResponseEntity.ok(getDocumentoNegocioById.execute(id));
    }


    @Override
    @PutMapping("/{id}")
    public ResponseEntity<DocumentoNegocioResponse> update(@PathVariable String id, @Valid @RequestBody DocumentoNegocioRequest request) {
        return ResponseEntity.ok(updateDocumentoNegocio.execute(id, request));
    }
}
