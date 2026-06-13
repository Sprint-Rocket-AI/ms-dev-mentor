package cl.sprint_rocket_ai.ms_context_builder.documents.application.doc_negocio;

import cl.sprint_rocket_ai.ms_context_builder.ai_index.application.AIIndexService;
import cl.sprint_rocket_ai.ms_context_builder.documents.domain.exceptions.EntityNotFoundException;
import cl.sprint_rocket_ai.ms_context_builder.documents.domain.models.DocumentoNegocio;
import cl.sprint_rocket_ai.ms_context_builder.documents.infrastructure.in.doc_negocio.dtos.DocumentoNegocioRequest;
import cl.sprint_rocket_ai.ms_context_builder.documents.infrastructure.in.doc_negocio.dtos.DocumentoNegocioResponse;
import cl.sprint_rocket_ai.ms_context_builder.documents.infrastructure.persistences.mongodb.DocumentoNegocioMongoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public final class UpdateDocumentoNegocio {

    private static final Logger log = LoggerFactory.getLogger(UpdateDocumentoNegocio.class);
    private final DocumentoNegocioMongoRepository repository;
    private final AIIndexService aiIndexService;

    public UpdateDocumentoNegocio(DocumentoNegocioMongoRepository documentoNegocioPortOut,
                                  AIIndexService aiIndexService) {
        this.repository = documentoNegocioPortOut;
        this.aiIndexService = aiIndexService;
    }

    public DocumentoNegocioResponse execute(String id, DocumentoNegocioRequest request) {
        log.info("Iniciando actualización de documento de negocio con id: {}", id);
        return repository.findById(id)
                .map(existing -> {
                    request.applyTo(existing);
                    existing.setFechaActualizacion(LocalDateTime.now());
                    DocumentoNegocio updated = repository.save(existing);
                    aiIndexService.update(updated);
                    log.info("Fin de la actualización del documento de negocio con id: {}", id);
                    return updated;
                })
                .map(DocumentoNegocioResponse::from)
                .orElseThrow(() -> new EntityNotFoundException("documento de negocio con id: " + id));
    }
}
