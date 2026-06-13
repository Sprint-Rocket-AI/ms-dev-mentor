package cl.sprint_rocket_ai.ms_context_builder.documents.application;

import cl.sprint_rocket_ai.ms_context_builder.documents.domain.models.DocumentoContexto;
import cl.sprint_rocket_ai.ms_context_builder.documents.domain.models.DocumentoResponse;
import cl.sprint_rocket_ai.ms_context_builder.documents.infrastructure.persistences.mongodb.DocumentoContextoMongoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public final class GetAllDocumentos {

    private static final Logger log = LoggerFactory.getLogger(GetAllDocumentos.class);
    private final DocumentoContextoMongoRepository repository;

    public GetAllDocumentos(DocumentoContextoMongoRepository repository) {
        this.repository = repository;
    }

    public List<DocumentoResponse> execute() {
        log.info("Iniciando Búsqueda de documentos");
        List<DocumentoResponse> result = repository.findAll()
                .stream()
                .map(DocumentoContexto::toResponse)
                .toList();
        log.info("Cantidad de documentos: {}", result.size());
        return result;
    }
}
