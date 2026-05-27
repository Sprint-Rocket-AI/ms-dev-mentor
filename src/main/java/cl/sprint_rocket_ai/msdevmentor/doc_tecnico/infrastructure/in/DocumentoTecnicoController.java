package cl.sprint_rocket_ai.msdevmentor.doc_tecnico.infrastructure.in;

import cl.sprint_rocket_ai.msdevmentor.doc_tecnico.application.GetDocumentoTecnicoById;
import cl.sprint_rocket_ai.msdevmentor.doc_tecnico.application.ListDocumentoTecnicoByProyecto;
import cl.sprint_rocket_ai.msdevmentor.doc_tecnico.application.SaveDocumentoTecnico;
import cl.sprint_rocket_ai.msdevmentor.doc_tecnico.application.UpdateDocumentoTecnico;
import cl.sprint_rocket_ai.msdevmentor.doc_tecnico.infrastructure.in.dtos.DocumentoTecnicoRequest;
import cl.sprint_rocket_ai.msdevmentor.doc_tecnico.infrastructure.in.dtos.DocumentoTecnicoResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/documentos-tecnicos")
public final class DocumentoTecnicoController implements DocumentoTecnicoRest {

    private final SaveDocumentoTecnico saveDocumentoTecnico;
    private final GetDocumentoTecnicoById getDocumentoTecnicoById;
    private final ListDocumentoTecnicoByProyecto listDocumentoTecnicoByProyecto;
    private final UpdateDocumentoTecnico updateDocumentoTecnico;

    public DocumentoTecnicoController(SaveDocumentoTecnico saveDocumentoTecnico, GetDocumentoTecnicoById getDocumentoTecnicoById, ListDocumentoTecnicoByProyecto listDocumentoTecnicoByProyecto, UpdateDocumentoTecnico updateDocumentoTecnico) {
        this.saveDocumentoTecnico = saveDocumentoTecnico;
        this.getDocumentoTecnicoById = getDocumentoTecnicoById;
        this.listDocumentoTecnicoByProyecto = listDocumentoTecnicoByProyecto;
        this.updateDocumentoTecnico = updateDocumentoTecnico;
    }

    @Override
    @PostMapping
    public ResponseEntity<DocumentoTecnicoResponse> create(@Valid @RequestBody DocumentoTecnicoRequest request) {
        return new ResponseEntity<>(saveDocumentoTecnico.execute(request), HttpStatus.CREATED);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<DocumentoTecnicoResponse> getById(@PathVariable String id) {
        return ResponseEntity.ok(getDocumentoTecnicoById.execute(id));
    }

    @Override
    @GetMapping("/proyecto/{proyectoId}")
    public ResponseEntity<List<DocumentoTecnicoResponse>> getByProyectoId(@PathVariable String proyectoId) {
        return ResponseEntity.ok(listDocumentoTecnicoByProyecto.execute(proyectoId));
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<DocumentoTecnicoResponse> update(@PathVariable String id, @Valid @RequestBody DocumentoTecnicoRequest request) {
        return ResponseEntity.ok(updateDocumentoTecnico.execute(id, request));

    }
}
