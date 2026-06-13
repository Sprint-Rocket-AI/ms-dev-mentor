package cl.sprint_rocket_ai.ms_context_builder.documents.application.doc_ddl;

import cl.sprint_rocket_ai.ms_context_builder.ai_index.application.AIIndexService;
import cl.sprint_rocket_ai.ms_context_builder.documents.domain.models.doc_ddl.DocumentoDDL;
import cl.sprint_rocket_ai.ms_context_builder.documents.infrastructure.in.doc_ddl.dtos.DocumentoDDLRequest;
import cl.sprint_rocket_ai.ms_context_builder.documents.infrastructure.in.doc_ddl.dtos.DocumentoDDLResponse;
import cl.sprint_rocket_ai.ms_context_builder.documents.infrastructure.persistences.mongodb.DocumentoDDLMongoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public final class SaveDocumentoDDL {

    private static final Logger log = LoggerFactory.getLogger(SaveDocumentoDDL.class);
    private final DocumentoDDLMongoRepository repository;
    private final AIIndexService aiIndexService;

    public SaveDocumentoDDL(DocumentoDDLMongoRepository repository,
                            AIIndexService aiIndexService) {
        this.repository = repository;
        this.aiIndexService = aiIndexService;
    }

    public DocumentoDDLResponse execute(DocumentoDDLRequest request) {
        log.info("Iniciando creación de documento DDL");
        DocumentoDDL documentoDDL = new DocumentoDDL();
        request.applyTo(documentoDDL);
        documentoDDL.setFechaCreacion(LocalDateTime.now());
        DocumentoDDL saved = repository.save(documentoDDL);
        aiIndexService.index(saved);
        log.info("Fin de la creación del documento DDL con id: {}", saved.getId());
        return DocumentoDDLResponse.from(saved);
    }
}
