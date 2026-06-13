package cl.sprint_rocket_ai.ms_context_builder.documents.application;

import cl.sprint_rocket_ai.ms_context_builder.ai_index.application.AIIndexService;
import cl.sprint_rocket_ai.ms_context_builder.documents.domain.exceptions.EntityNotFoundException;
import cl.sprint_rocket_ai.ms_context_builder.documents.infrastructure.persistences.mongodb.DocumentoContextoMongoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public final class DeleteDocumentoById {

    private static final Logger log = LoggerFactory.getLogger(DeleteDocumentoById.class);
    private final DocumentoContextoMongoRepository repository;
    private final AIIndexService aiIndexService;

    public DeleteDocumentoById(DocumentoContextoMongoRepository repository,
                               AIIndexService aiIndexService) {
        this.repository = repository;
        this.aiIndexService = aiIndexService;
    }

    public void execute(String id) {
        log.info("Eliminando documento por id: {}", id);
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("documento con id: " + id);
        }

        aiIndexService.deleteById(id);
        repository.deleteById(id);
        log.info("Documento con id: {} eliminado exitosamente", id);
    }
}
