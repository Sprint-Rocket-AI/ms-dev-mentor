package cl.sprint_rocket_ai.ms_context_builder.documents.infrastructure.in;

import cl.sprint_rocket_ai.ms_context_builder.documents.application.GetAllDocumentos;
import cl.sprint_rocket_ai.ms_context_builder.documents.application.GetDocumentoById;
import cl.sprint_rocket_ai.ms_context_builder.documents.domain.models.DocumentoResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/documentos-contexto")
public final class DocumentoRestController implements DocumentoController {

    private final GetAllDocumentos getAllDocumentos;
    private final GetDocumentoById getDocumentoById;

    public DocumentoRestController(GetAllDocumentos getAllDocumentos,
                                   GetDocumentoById getDocumentoById) {
        this.getAllDocumentos = getAllDocumentos;
        this.getDocumentoById = getDocumentoById;
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<DocumentoResponse> getById(@PathVariable String id) {
        return ResponseEntity.ok(getDocumentoById.execute(id));

    }

    @Override
    @GetMapping
    public ResponseEntity<List<DocumentoResponse>> getAll() {
        return ResponseEntity.ok(getAllDocumentos.execute());
    }

}
