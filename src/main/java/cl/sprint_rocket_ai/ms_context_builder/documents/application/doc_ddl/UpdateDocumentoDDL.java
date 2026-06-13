package cl.sprint_rocket_ai.ms_context_builder.documents.application.doc_ddl;

import cl.sprint_rocket_ai.ms_context_builder.ai_index.application.AIIndexService;
import cl.sprint_rocket_ai.ms_context_builder.documents.domain.exceptions.EntityNotFoundException;
import cl.sprint_rocket_ai.ms_context_builder.documents.domain.models.DocumentoDDL;
import cl.sprint_rocket_ai.ms_context_builder.documents.infrastructure.in.doc_ddl.dtos.DocumentoDDLRequest;
import cl.sprint_rocket_ai.ms_context_builder.documents.infrastructure.in.doc_ddl.dtos.DocumentoDDLResponse;
import cl.sprint_rocket_ai.ms_context_builder.documents.infrastructure.persistences.mongodb.DocumentoDDLMongoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public final class UpdateDocumentoDDL {

    private static final Logger log = LoggerFactory.getLogger(UpdateDocumentoDDL.class);
    private final DocumentoDDLMongoRepository repository;
    private final AIIndexService aiIndexService;

    public UpdateDocumentoDDL(DocumentoDDLMongoRepository repository,
                              AIIndexService aiIndexService) {
        this.repository = repository;
        this.aiIndexService = aiIndexService;
    }

    public DocumentoDDLResponse execute(String id, DocumentoDDLRequest request) {
        log.info("Iniciando actualización de documento DDL con id: {}", id);
        return repository.findById(id)
                .map(existing -> {
                    request.applyTo(existing);
                    existing.setFechaActualizacion(LocalDateTime.now());
                    DocumentoDDL updated = repository.save(existing);
                    aiIndexService.update(updated);
                    log.info("Fin de la actualización del documento DDL con id: {}", id);
                    return updated;
                })
                .map(DocumentoDDLResponse::from)
                .orElseThrow(() -> new EntityNotFoundException("documento DDL con id: " + id));
    }
}
