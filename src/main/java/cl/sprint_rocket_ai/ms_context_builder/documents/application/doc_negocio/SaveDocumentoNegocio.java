package cl.sprint_rocket_ai.ms_context_builder.documents.application.doc_negocio;

import cl.sprint_rocket_ai.ms_context_builder.ai_index.application.AIIndexService;
import cl.sprint_rocket_ai.ms_context_builder.documents.domain.models.DocumentoNegocio;
import cl.sprint_rocket_ai.ms_context_builder.documents.infrastructure.in.doc_negocio.dtos.DocumentoNegocioRequest;
import cl.sprint_rocket_ai.ms_context_builder.documents.infrastructure.in.doc_negocio.dtos.DocumentoNegocioResponse;
import cl.sprint_rocket_ai.ms_context_builder.documents.infrastructure.persistences.mongodb.DocumentoNegocioMongoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public final class SaveDocumentoNegocio {

    private static final Logger log = LoggerFactory.getLogger(SaveDocumentoNegocio.class);
    private final DocumentoNegocioMongoRepository repository;
    private final AIIndexService aiIndexService;

    public SaveDocumentoNegocio(DocumentoNegocioMongoRepository repository,
                                AIIndexService aiIndexService) {
        this.repository = repository;
        this.aiIndexService = aiIndexService;
    }

    public DocumentoNegocioResponse execute(DocumentoNegocioRequest request) {
        log.info("Iniciando creación de documento negocio");
        DocumentoNegocio documentoNegocio = new DocumentoNegocio();
        request.applyTo(documentoNegocio);
        documentoNegocio.setFechaCreacion(LocalDateTime.now());
        DocumentoNegocio saved = repository.save(documentoNegocio);
        aiIndexService.index(saved);
        log.info("Fin de la creación del documento negocio con id: {}", saved.getId());
        return DocumentoNegocioResponse.from(saved);
    }
}
