package cl.sprint_rocket_ai.ms_context_builder.documents.application.doc_sistema;

import cl.sprint_rocket_ai.ms_context_builder.ai_index.application.AIIndexService;
import cl.sprint_rocket_ai.ms_context_builder.documents.domain.exceptions.EntityNotFoundException;
import cl.sprint_rocket_ai.ms_context_builder.documents.domain.models.DocumentoSistema;
import cl.sprint_rocket_ai.ms_context_builder.documents.infrastructure.in.doc_sistema.dtos.DocumentoSistemaRequest;
import cl.sprint_rocket_ai.ms_context_builder.documents.infrastructure.in.doc_sistema.dtos.DocumentoSistemaResponse;
import cl.sprint_rocket_ai.ms_context_builder.documents.infrastructure.persistences.mongodb.DocumentoSistemaMongoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public final class UpdateDocumentoSistema {

    private static final Logger log = LoggerFactory.getLogger(UpdateDocumentoSistema.class);
    private final DocumentoSistemaMongoRepository repository;
    private final AIIndexService aiIndexService;

    public UpdateDocumentoSistema(DocumentoSistemaMongoRepository repository,
                                  AIIndexService aiIndexService) {
        this.repository = repository;
        this.aiIndexService = aiIndexService;
    }

    public DocumentoSistemaResponse execute(String id, DocumentoSistemaRequest documentoSistema) {
        log.info("Iniciando actualización de documento sistema con id: {}", id);
        return repository.findById(id)
                .map(existingDocument -> {
                    existingDocument.setTitulo(documentoSistema.titulo());
                    existingDocument.setContenido(documentoSistema.contenido());
                    existingDocument.setUrlRepos(documentoSistema.urlRepos());
                    existingDocument.setStack(documentoSistema.stack());
                    existingDocument.setDevs(documentoSistema.devs());
                    existingDocument.setTags(documentoSistema.tags());
                    existingDocument.setFechaActualizacion(LocalDateTime.now());
                    DocumentoSistema updatedDocument = repository.save(existingDocument);
                    aiIndexService.update(updatedDocument);
                    log.info("Fin de la actualizacion del documento sistema con id: {}", id);
                    return updatedDocument;
                })
                .map(DocumentoSistemaResponse::from)
                .orElseThrow(()-> new EntityNotFoundException("documento sistema con id: " + id));
    }
}
