package cl.sprint_rocket_ai.ms_context_builder.documents.application.doc_lineamiento;

import cl.sprint_rocket_ai.ms_context_builder.ai_index.application.AIIndexService;
import cl.sprint_rocket_ai.ms_context_builder.documents.domain.models.DocumentoLineamiento;
import cl.sprint_rocket_ai.ms_context_builder.documents.infrastructure.in.doc_lineamiento.dtos.DocumentoLineamientoRequest;
import cl.sprint_rocket_ai.ms_context_builder.documents.infrastructure.in.doc_lineamiento.dtos.DocumentoLineamientoResponse;
import cl.sprint_rocket_ai.ms_context_builder.documents.infrastructure.persistences.mongodb.DocumentoLineamientoMongoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public final class SaveDocumentoLineamiento {

    private static final Logger log = LoggerFactory.getLogger(SaveDocumentoLineamiento.class);
    private final DocumentoLineamientoMongoRepository repository;
    private final AIIndexService aiIndexService;

    public SaveDocumentoLineamiento(DocumentoLineamientoMongoRepository repository,
                                    AIIndexService aiIndexService) {
        this.repository = repository;
        this.aiIndexService = aiIndexService;
    }

    public DocumentoLineamientoResponse execute(DocumentoLineamientoRequest request) {
        log.info("Iniciando creación de documento lineamiento");
        DocumentoLineamiento documentoLineamiento = new DocumentoLineamiento();
        request.applyTo(documentoLineamiento);
        documentoLineamiento.setFechaCreacion(LocalDateTime.now());
        DocumentoLineamiento persistido = repository.save(documentoLineamiento);
        aiIndexService.index(persistido);
        log.info("Fin de la creación del documento lineamiento con id: {}", persistido.getId());
        return DocumentoLineamientoResponse.from(persistido);
    }
}
