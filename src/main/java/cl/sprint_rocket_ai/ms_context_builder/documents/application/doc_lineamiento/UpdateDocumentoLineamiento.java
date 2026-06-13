package cl.sprint_rocket_ai.ms_context_builder.documents.application.doc_lineamiento;

import cl.sprint_rocket_ai.ms_context_builder.documents.domain.exceptions.EntityNotFoundException;
import cl.sprint_rocket_ai.ms_context_builder.documents.infrastructure.in.doc_lineamiento.dtos.DocumentoLineamientoRequest;
import cl.sprint_rocket_ai.ms_context_builder.documents.infrastructure.in.doc_lineamiento.dtos.DocumentoLineamientoResponse;
import cl.sprint_rocket_ai.ms_context_builder.documents.infrastructure.persistences.mongodb.DocumentoLineamientoMongoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public final class UpdateDocumentoLineamiento {

    private static final Logger log = LoggerFactory.getLogger(UpdateDocumentoLineamiento.class);
    private final DocumentoLineamientoMongoRepository repository;

    public UpdateDocumentoLineamiento(DocumentoLineamientoMongoRepository repository) {
        this.repository = repository;
    }

    public DocumentoLineamientoResponse execute(String id, DocumentoLineamientoRequest request) {
        log.info("Iniciando actualización de documento lineamiento con id: {}", id);
        return repository.findById(id)
                .map( existing -> {
                    request.applyTo(existing);
                    existing.setFechaActualizacion(LocalDateTime.now());
                    return repository.save(existing);
                })
                .map(DocumentoLineamientoResponse::from)
                .map(response -> {
                    log.info("Fin de la actualización del documento lineamiento con id: {}", id);
                    return response;
                })
                .orElseThrow(() -> new EntityNotFoundException("documento lineamiento con id: " + id));
    }
}

