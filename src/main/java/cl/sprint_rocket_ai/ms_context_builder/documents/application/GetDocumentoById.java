package cl.sprint_rocket_ai.ms_context_builder.documents.application;

import cl.sprint_rocket_ai.ms_context_builder.documents.domain.exceptions.EntityNotFoundException;
import cl.sprint_rocket_ai.ms_context_builder.documents.domain.models.DocumentoContexto;
import cl.sprint_rocket_ai.ms_context_builder.documents.domain.models.DocumentoResponse;
import cl.sprint_rocket_ai.ms_context_builder.documents.infrastructure.persistences.mongodb.DocumentoContextoMongoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public final class GetDocumentoById {

    private static final Logger log = LoggerFactory.getLogger(GetDocumentoById.class);
    private final DocumentoContextoMongoRepository repository;

    public GetDocumentoById(DocumentoContextoMongoRepository repository) {
        this.repository = repository;
    }

    public DocumentoResponse execute(String id) {
        log.info("Iniciando búsqueda de documento por id: {}", id);
        return repository.findById(id)
                .map(DocumentoContexto::toResponse)
                .orElseThrow(()-> new EntityNotFoundException("documento sistema con id: " + id));
    }
}
