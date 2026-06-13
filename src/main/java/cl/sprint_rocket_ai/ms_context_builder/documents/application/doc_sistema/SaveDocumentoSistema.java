package cl.sprint_rocket_ai.ms_context_builder.documents.application.doc_sistema;

import cl.sprint_rocket_ai.ms_context_builder.ai_index.application.AIIndexService;
import cl.sprint_rocket_ai.ms_context_builder.documents.domain.models.DocumentoSistema;
import cl.sprint_rocket_ai.ms_context_builder.documents.infrastructure.in.doc_sistema.dtos.DocumentoSistemaRequest;
import cl.sprint_rocket_ai.ms_context_builder.documents.infrastructure.in.doc_sistema.dtos.DocumentoSistemaResponse;
import cl.sprint_rocket_ai.ms_context_builder.documents.infrastructure.persistences.mongodb.DocumentoSistemaMongoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public final class SaveDocumentoSistema {

    private static final Logger log = LoggerFactory.getLogger(SaveDocumentoSistema.class);
    private final DocumentoSistemaMongoRepository repository;
    private final AIIndexService aiIndexService;

    public SaveDocumentoSistema(DocumentoSistemaMongoRepository repository,
                                AIIndexService aiIndexService) {
        this.repository = repository;
        this.aiIndexService = aiIndexService;
    }

    public DocumentoSistemaResponse execute(DocumentoSistemaRequest request) {
        log.info("Iniciando creación de documento sistema");
        DocumentoSistema documentoSistema = new DocumentoSistema();
        documentoSistema.setTitulo(request.titulo());
        documentoSistema.setContenido(request.contenido());
        documentoSistema.setUrlRepos(request.urlRepos());
        documentoSistema.setStack(request.stack());
        documentoSistema.setDevs(request.devs());
        documentoSistema.setTags(request.tags());
        documentoSistema.setFechaCreacion(LocalDateTime.now());
        DocumentoSistema documentoPersistido = repository.save(documentoSistema);
        aiIndexService.index(documentoPersistido);
        log.info("Fin de la creación del documento sistema con id: {}", documentoPersistido.getId());
        return DocumentoSistemaResponse.from(documentoPersistido);
    }
}
