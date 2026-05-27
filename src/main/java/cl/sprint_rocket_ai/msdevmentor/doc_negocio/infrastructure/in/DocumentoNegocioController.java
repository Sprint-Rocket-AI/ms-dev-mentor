package cl.sprint_rocket_ai.msdevmentor.doc_negocio.infrastructure.in;

import cl.sprint_rocket_ai.msdevmentor.doc_negocio.application.GetDocumentoNegocioById;
import cl.sprint_rocket_ai.msdevmentor.doc_negocio.application.ListDocumentoNegocioByProyecto;
import cl.sprint_rocket_ai.msdevmentor.doc_negocio.application.SaveDocumentoNegocio;
import cl.sprint_rocket_ai.msdevmentor.doc_negocio.application.UpdateDocumentoNegocio;
import cl.sprint_rocket_ai.msdevmentor.doc_negocio.infrastructure.in.dtos.DocumentoNegocioRequest;
import cl.sprint_rocket_ai.msdevmentor.doc_negocio.infrastructure.in.dtos.DocumentoNegocioResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/documentos-negocio")
public final class DocumentoNegocioController implements DocumentoNegocioRest {

    private final SaveDocumentoNegocio saveDocumentoNegocio;
    private final GetDocumentoNegocioById getDocumentoNegocioById;
    private final ListDocumentoNegocioByProyecto listDocumentoNegocioByProyecto;
    private final UpdateDocumentoNegocio updateDocumentoNegocio;

    public DocumentoNegocioController(SaveDocumentoNegocio saveDocumentoNegocio, GetDocumentoNegocioById getDocumentoNegocioById, ListDocumentoNegocioByProyecto listDocumentoNegocioByProyecto, UpdateDocumentoNegocio updateDocumentoNegocio) {
        this.saveDocumentoNegocio = saveDocumentoNegocio;
        this.getDocumentoNegocioById = getDocumentoNegocioById;
        this.listDocumentoNegocioByProyecto = listDocumentoNegocioByProyecto;
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
    @GetMapping("/proyecto/{proyectoId}")
    public ResponseEntity<List<DocumentoNegocioResponse>> getByProyectoId(@PathVariable String proyectoId) {
        return ResponseEntity.ok(listDocumentoNegocioByProyecto.execute(proyectoId));
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<DocumentoNegocioResponse> update(@PathVariable String id, @Valid @RequestBody DocumentoNegocioRequest request) {
        return ResponseEntity.ok(updateDocumentoNegocio.execute(id, request));
    }
}
